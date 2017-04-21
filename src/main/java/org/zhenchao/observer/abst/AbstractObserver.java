package org.zhenchao.observer.abst;

/**
 * 抽象观察者
 *
 * @author zhenchao.wang 2017-04-21 16:07
 * @version 1.0.0
 */
public abstract class AbstractObserver {

    /**
     * 收到通知后触发的更新操作
     */
    public abstract void update();
}
