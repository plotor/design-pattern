package org.zhenchao.proxy;

/**
 * @author zhenchao.wang 2019-11-30 14:04
 * @version 1.0.0
 */
public class Proxy implements Subject {

    /** 被代理对象 */
    private RealSubject subject;

    public Proxy(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        // 前置处理，可选

        // 委托被代理对象
        subject.request();

        // 后置处理，可选
    }

}
