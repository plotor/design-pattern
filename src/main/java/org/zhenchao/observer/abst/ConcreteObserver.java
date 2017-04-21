package org.zhenchao.observer.abst;

/**
 * 具体观察者
 *
 * @author zhenchao.wang 2017-04-21 16:16
 * @version 1.0.0
 */
public class ConcreteObserver extends AbstractObserver {

    @Override
    public void update() {
        System.out.println(this.getClass() + " is notified~");
    }
}
