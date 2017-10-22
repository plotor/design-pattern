package org.zhenchao.reactor.bad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author zhenchao.wang 2017-10-21 22:02
 * @version 1.0.0
 */
public class Server implements Runnable {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server listening on port: " + this.port);
            while (!Thread.interrupted())
                // 启动一个新的线程处理当前请求，也可以引入线程池
                new Thread(new Handler(ss.accept())).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class Handler implements Runnable {

        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("[thread-" + Thread.currentThread().getId() + "] is processing data from client.");
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = this.process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private byte[] process(byte[] cmd) {
            String result = "Hello " + new String(cmd);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result.getBytes();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8080)).start();
    }
}
