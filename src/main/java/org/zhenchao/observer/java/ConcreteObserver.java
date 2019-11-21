package org.zhenchao.observer.java;

import java.util.Observable;
import java.util.Observer;

/**
 * 基于java原生支持的具体观察者
 *
 * @author zhenchao.wang 2017-04-21 16:26
 * @version 1.0.0
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update(Observable subject, Object arg) {
        System.out.println(this.getClass().getClass() + ", data changed : " + ((ConcreteSubject) subject).getData());
    }

}
