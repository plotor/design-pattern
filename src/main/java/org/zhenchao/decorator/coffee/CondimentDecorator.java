package org.zhenchao.decorator.coffee;

/**
 * 调料装饰器
 *
 * @author zhenchao.wang 2016-12-03 12:29
 * @version 1.0.0
 */
public abstract class CondimentDecorator extends Coffee {

    protected static final String SEP = ", ";

    protected Coffee coffee;

    public CondimentDecorator(String name, Coffee coffee) {
        super(name);
        this.coffee = coffee;
    }

    /**
     * 标签信息
     *
     * @return
     */
    public abstract String getName();
}
