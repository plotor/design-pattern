package org.zhenchao.reactor.stuff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:40
 * @version 1.0.0
 */
public class HandlerWithoutThreadPool implements Callable<Boolean> {

    protected final SocketChannel socketChannel;
    protected final SelectionKey selectionKey;

    protected ByteBuffer input = ByteBuffer.allocate(1024);

    protected static final int READING = 0, SENDING = 1;
    protected int state = READING;

    private String clientName = "";

    public HandlerWithoutThreadPool(Selector selector, SocketChannel channel) throws IOException {
        this.socketChannel = channel;
        this.socketChannel.configureBlocking(false);
        this.selectionKey = this.socketChannel.register(selector, 0);
        this.selectionKey.attach(this);
        this.selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public Boolean call() throws Exception {
        try {
            if (state == READING) {
                this.read();
            } else if (state == SENDING) {
                this.send();
            }
            return true;
        } catch (IOException e) {
            socketChannel.close();
        }
        return false;
    }

    protected void read() throws IOException {
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            this.readProcess(readCount);
        }
        state = SENDING;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    protected void send() throws IOException {
        System.out.println("Saying hello to " + clientName);
        ByteBuffer output = ByteBuffer.wrap(("Hello, " + clientName + "\n").getBytes());
        socketChannel.write(output);
        selectionKey.interestOps(SelectionKey.OP_READ);
        state = READING;
    }

    protected synchronized void readProcess(int readCount) {
        StringBuilder sb = new StringBuilder();
        input.flip();
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array, 0, subStringBytes, 0, readCount);
        // Assuming ASCII (bad assumption but simplifies the example)
        sb.append(new String(subStringBytes));
        input.clear();
        clientName = sb.toString().trim();
    }

}
