package org.zhenchao.interpreter;

/**
 * 抽象表达式
 *
 * @author zhenchao.wang 2019-11-30 16:55
 * @version 1.0.0
 */
public abstract class Expression {

    /**
     * 解释操作
     *
     * @param ctx
     */
    public abstract void interpret(Context ctx);

}
