package org.zhenchao.reactor;

/**
 * @author zhenchao.wang 2017-10-22 15:33
 * @version 1.0.0
 */
public class Server {

    public static void main(String[] args) throws Exception {
        new Thread(new Reactor(8080)).start();
    }

}
