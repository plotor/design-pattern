package org.zhenchao.decorator.coffee;

/**
 * 黑咖啡
 *
 * @author zhenchao.wang 2016-12-03 12:23
 * @version 1.0.0
 */
public class BlackCoffee extends Coffee {

    public BlackCoffee() {
        super("black coffee");
    }

    @Override
    public double price() {
        return 8.0;
    }
}
