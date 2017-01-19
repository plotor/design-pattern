package org.zhenchao.reactor.stuff;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:41
 * @version 1.0.0
 */
public class HandlerWithThreadPool extends Handler {

    private class Processor implements Runnable {

        private int readCount;

        Processor(int readCount) {
            this.readCount = readCount;
        }

        public void run() {
            processAndHandOff(readCount);
        }
    }

    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    private static final int PROCESSING = 2;

    public HandlerWithThreadPool(Selector selector, SocketChannel channel) throws IOException {
        super(selector, channel);
    }

    public void read() throws IOException {
        int readCount = socketChannel.read(input);
        if (readCount > 0) {
            state = PROCESSING;
            pool.execute(new Processor(readCount));
        }
        //We are interested in writing back to the client soon after read processing is done.
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    //Start processing in a new Processor Thread and Hand off to the reactor thread.
    public synchronized void processAndHandOff(int readCount) {
        this.readProcess(readCount);
        //Read processing done. Now the server is ready to send a message to the client.
        state = SENDING;
    }

}
