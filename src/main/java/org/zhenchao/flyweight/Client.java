package org.zhenchao.flyweight;

/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-09 18:16
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();
        Flyweight flyweight = factory.getFlyweight("any key");
        flyweight.operate("any external state");
    }

}
