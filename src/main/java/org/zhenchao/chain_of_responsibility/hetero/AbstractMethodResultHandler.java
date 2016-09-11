package org.zhenchao.chain_of_responsibility.hetero;

import org.zhenchao.chain_of_responsibility.model.Result;

/**
 * 函数结果处理器抽象类
 *
 * @author zhenchao.wang 2016-09-11 16:47
 * @version 1.0.0
 */
public abstract class AbstractMethodResultHandler {

    protected AbstractMethodResultHandler nextHandler;

    public AbstractMethodResultHandler() {
    }

    public AbstractMethodResultHandler(AbstractMethodResultHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(Result result);

    public void setNextHandler(AbstractMethodResultHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
