package org.zhenchao.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:40
 * @version 1.0.0
 */
public class Handler implements Runnable {

    private static final int READ = 0, PROCESS = 1, WRITE = 2;

    private final SocketChannel socketChannel;
    private final SelectionKey selectionKey;

    // private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    private ByteBuffer input = ByteBuffer.allocate(1024);
    private boolean isClosed;
    private int state = READ;
    private String data;

    public Handler(Selector selector, SocketChannel channel) throws IOException {
        this.socketChannel = channel;
        this.socketChannel.configureBlocking(false);
        this.isClosed = !socketChannel.isConnected();
        this.selectionKey = this.socketChannel.register(selector, 0);
        this.selectionKey.attach(this);
        this.selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (isClosed) socketChannel.close();
            if (READ == state) {
                this.read();
            } else if (WRITE == state) {
                this.write();
            }
        } catch (IOException e) {
            try {
                socketChannel.close();
            } catch (IOException e1) {
                // ignore
            }
        }
    }

    private void read() throws IOException {
        System.out.println("[thread-" + Thread.currentThread().getId() + "] read data from client.");
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            state = PROCESS;
            pool.execute(() -> this.process(readCount));
        } else {
            this.isClosed = true;
        }
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private void process(int readCount) {
        System.out.println("[thread-" + Thread.currentThread().getId() + "] is processing data.");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        input.flip();
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array, 0, subStringBytes, 0, readCount);
        sb.append(new String(subStringBytes));
        input.clear();
        this.data = sb.toString().trim();
        state = WRITE;
    }

    private void write() throws IOException {
        System.out.println("[thread-" + Thread.currentThread().getId() + "] write data to client : " + this.data);
        ByteBuffer output = ByteBuffer.wrap(("Hello " + this.data + "\n").getBytes());
        socketChannel.write(output);
        selectionKey.interestOps(SelectionKey.OP_READ);
        state = READ;
    }

}
