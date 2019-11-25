package org.zhenchao.chain.demo;

/**
 * 抽象处理者
 *
 * @author zhenchao.wang 2016-09-07 23:30
 * @version 1.0.0
 */
public abstract class Handler {

    /** 后继处理器 */
    protected Handler next;

    public abstract void handle();

    public void setNext(Handler next) {
        this.next = next;
    }
}
