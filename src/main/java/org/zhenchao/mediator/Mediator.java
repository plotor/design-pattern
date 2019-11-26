package org.zhenchao.mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:05
 * @version 1.0.0
 */
public interface Mediator {

    /**
     * 参与对象在状态发生改变时通知中介者，让中介者去负责与其它参与对象进行交互
     *
     * @param colleague
     */
    void changed(Colleague colleague);

}
