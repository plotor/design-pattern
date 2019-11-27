package org.zhenchao.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题
 * 考虑到大部分主题对于下面这三个方法的实现都几乎相同，所以这些方法可以抽象到抽象主题中实现
 *
 * @author zhenchao.wang 2017-04-21 16:09
 * @version 1.0.0
 */
public class ConcreteSubject extends Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    protected void notifyObservers() {
        for (final Observer observer : observers) {
            observer.update(this);
        }
    }

}
