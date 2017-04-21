package org.zhenchao.observer.abst;

import java.util.LinkedList;
import java.util.List;

/**
 * 具体主题
 * 考虑到大部分主题对于下面这三个方法的实现都几乎相同，所以这些方法可以抽象到抽象主题中实现
 *
 * @author zhenchao.wang 2017-04-21 16:09
 * @version 1.0.0
 */
public class ConcreteSubject extends AbstractSubject {

    private List<AbstractObserver> observers = new LinkedList<>();

    @Override
    public void attach(AbstractObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(AbstractObserver observer) {
        observers.remove(observer);
    }

    @Override
    protected void notifyObservers() {
        for (final AbstractObserver observer : observers) {
            observer.update();
        }
    }

}
