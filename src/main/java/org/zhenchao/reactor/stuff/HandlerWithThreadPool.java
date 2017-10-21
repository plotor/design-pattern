package org.zhenchao.reactor.stuff;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhenchao.wang 2017-10-21 15:05
 * @version 1.0.0
 */
public class HandlerWithThreadPool extends Handler {

    static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static final int PROCESSING = 2;

    public HandlerWithThreadPool(Selector selector, SocketChannel socketChannel) throws IOException {
        super(selector, socketChannel);
    }

    void read() throws IOException {
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            state = PROCESSING;
            pool.execute(new Processer(readCount));
        } else {
            close = true;
        }
        //We are interested in writing back to the client soon after read processing is done.
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    //Start processing in a new Processer Thread and Hand off to the reactor thread.
    void processAndHandOff(int readCount) {
        readProcess(readCount);
        //Read processing done. Now the server is ready to send a message to the client.
        state = SENDING;
    }

    class Processer implements Runnable {
        int readCount;

        Processer(int readCount) {
            this.readCount = readCount;
        }

        public void run() {
            processAndHandOff(readCount);
        }
    }
}
