package org.zhenchao.decorator.coffee;

import org.apache.commons.lang3.StringUtils;

/**
 * 调味品：牛奶
 *
 * @author zhenchao.wang 2016-12-03 12:34
 * @version 1.0.0
 */
public class MilkDecorator extends CondimentDecorator {

    private static final String NAME = "milk";

    public MilkDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 5.6;
    }

    @Override
    public String getName() {
        return StringUtils.join(coffee.getName(), SEP, NAME);
    }
}
