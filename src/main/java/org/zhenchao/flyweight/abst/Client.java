package org.zhenchao.flyweight.abst;

/**
 * @author zhenchao.wang 2017-04-09 18:16
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();
        AbstractFlyweight flyweight = factory.getFlyweight("any key");
        flyweight.operate("any external state");
    }

}
