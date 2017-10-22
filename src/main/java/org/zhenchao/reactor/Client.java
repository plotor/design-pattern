package org.zhenchao.reactor;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * http://jeewanthad.blogspot.hk/2013/02/reactor-pattern-explained-part-1.html
 *
 * @author zhenchao.wang 2017-01-18 22:26
 * @version 1.0.0
 */
public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void sayHello() throws Exception {
        int clientSize = 1024;
        ExecutorService es = Executors.newFixedThreadPool(clientSize);
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < clientSize; i++) {
            tasks.add(() -> {
                Socket socket = null;
                PrintWriter out = null;
                BufferedReader in = null;
                try {
                    socket = new Socket(host, port);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Client[" + Thread.currentThread().getId() + "] connect success, host : " + host + " port: " + port);
                    String hay = RandomStringUtils.randomAlphanumeric(32);
                    out.println(hay);
                    String msg = in.readLine().trim();
                    System.out.println("Client[" + Thread.currentThread().getId() + "] receive data from server : " + msg);
                    if(!("Hello " + hay).equals(msg)) {
                        System.err.println("expect : " + hay + ", but : " + msg);
                        System.exit(-1);
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) out.close();
                    if (null != in) in.close();
                    if (null != socket) socket.close();
                }
                return false;
            });
        }
        List<Future<Boolean>> futures = es.invokeAll(tasks);
        for (final Future<Boolean> future : futures) {
            future.get();
        }
        TimeUnit.SECONDS.sleep(5);
        es.shutdown();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client("127.0.0.1", 8080);
        client.sayHello();
    }

}
