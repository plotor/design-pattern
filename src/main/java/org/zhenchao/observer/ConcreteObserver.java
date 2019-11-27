package org.zhenchao.observer;

/**
 * 具体观察者
 *
 * @author zhenchao.wang 2017-04-21 16:16
 * @version 1.0.0
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update(Subject subject) {
        // 可以通过 subject 对象获取目标对象的状态信息
        System.out.println(this.getClass() + " is notified.");
    }

}
