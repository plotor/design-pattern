package org.zhenchao.observer.abst;

/**
 * 抽象主题
 *
 * @author zhenchao.wang 2017-04-21 16:04
 * @version 1.0.0
 */
public abstract class AbstractSubject {

    /**
     * 注册观察者
     *
     * @param observer
     */
    public abstract void attach(AbstractObserver observer);

    /**
     * 注销观察者
     *
     * @param observer
     */
    public abstract void detach(AbstractObserver observer);

    /**
     * 通知在册的所有观察者
     */
    protected abstract void notifyObservers();
}
