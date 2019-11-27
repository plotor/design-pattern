package org.zhenchao.observer.java;

/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-21 16:32
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver());
        subject.addObserver(new ConcreteObserver());
        subject.setData("aaa");
        subject.setData("bbb");
        subject.setData("ccc");
    }

}
