package org.zhenchao.decorator.coffee;

/**
 * 调料装饰器
 *
 * @author zhenchao.wang 2016-12-03 12:29
 * @version 1.0.0
 */
public abstract class CondimentDecorator extends Coffee {

    // 需要持有一个被装饰组件对象
    protected Coffee coffee;

    public CondimentDecorator(String name, Coffee coffee) {
        super(name);
        this.coffee = coffee;
    }

    @Override
    public abstract String getName();

}
