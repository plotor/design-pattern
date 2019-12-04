package org.zhenchao.interpreter.grind;

/**
 * 抽象解释器
 *
 * @author zhenchao.wang 2019-12-04 13:50
 * @version 1.0.0
 */
public abstract class XmlExpression {

    /**
     * 解释表达式
     *
     * @param context
     * @return
     */
    public abstract String[] interpret(Context context);

}
