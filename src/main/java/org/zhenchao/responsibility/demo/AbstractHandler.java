package org.zhenchao.responsibility.demo;

/**
 * 抽象处理者
 *
 * @author zhenchao.wang 2016-09-07 23:30
 * @version 1.0.0
 */
public abstract class AbstractHandler {

    /** 后继处理器 */
    protected AbstractHandler nextHandler;

    /**
     * 处理方法
     */
    public abstract void handle();

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
