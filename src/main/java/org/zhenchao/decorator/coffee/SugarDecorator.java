package org.zhenchao.decorator.coffee;

/**
 * 调味品：糖
 *
 * @author zhenchao.wang 2016-12-03 12:45
 * @version 1.0.0
 */
public class SugarDecorator extends CondimentDecorator {

    private static final String NAME = "sugar";

    public SugarDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 1.8D;
    }

    @Override
    public String getName() {
        return coffee.getName() + ", " + NAME;
    }

}
