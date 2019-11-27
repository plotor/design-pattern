package org.zhenchao.observer;

/**
 * 抽象观察者
 *
 * @author zhenchao.wang 2017-04-21 16:07
 * @version 1.0.0
 */
public interface Observer {

    /**
     * 收到通知后触发更新操作
     *
     * @param subject 观察的目标对象
     */
    void update(Subject subject);

}
