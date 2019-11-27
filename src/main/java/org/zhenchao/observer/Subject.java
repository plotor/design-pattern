package org.zhenchao.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * 抽象主题
 *
 * @author zhenchao.wang 2017-04-21 16:04
 * @version 1.0.0
 */
public abstract class Subject {

    private List<Observer> observers = new LinkedList<>();

    /**
     * 注册观察者
     *
     * @param observer
     */
    public void register(Observer observer) {
        observers.add(observer);
    }

    /**
     * 注销观察者
     *
     * @param observer
     */
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知在册的所有观察者
     */
    protected void notifyObservers() {
        for (final Observer observer : observers) {
            observer.update(this);
        }
    }

}
