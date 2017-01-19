package org.zhenchao.reactor.stuff;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:35
 * @version 1.0.0
 */
public class Reactor implements Runnable {

    private class Acceptor implements Runnable {
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    if (useThreadPool) {
                        // 采用线程池
                        new HandlerWithThreadPool(selector, socketChannel);
                    } else {
                        // 不采用线程池
                        new Handler(selector, socketChannel);
                    }
                }
                System.out.println("Connection Accepted by Reactor!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private final Selector selector;

    private final ServerSocketChannel serverSocketChannel;

    private final boolean useThreadPool;

    public Reactor(int port, boolean useThreadPool) throws IOException {
        this.useThreadPool = useThreadPool;
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    /**
     * Reactor 轮询选择
     */
    public void run() {
        System.out.println("Server listening to port: " + serverSocketChannel.socket().getLocalPort());
        try {
            while (!Thread.interrupted()) {
                // 轮询选择
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator itr = selected.iterator();
                while (itr.hasNext()) {
                    this.dispatch((SelectionKey) (itr.next()));
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分派任务
     *
     * @param key
     */
    private void dispatch(SelectionKey key) {
        Runnable acceptor = (Runnable) (key.attachment());
        if (acceptor != null) {
            // 调用Accepter的run方法
            acceptor.run();
        }
    }

    /**
     * 驱动函数
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Thread(new Reactor(8080, false)).start();
    }

}
