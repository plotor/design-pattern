package org.zhenchao.decorator.coffee;

/**
 * 白咖啡
 *
 * @author zhenchao.wang 2016-12-03 12:17
 * @version 1.0.0
 */
public class WhiteCoffee extends Coffee {

    public WhiteCoffee() {
        super("white coffee");
    }

    @Override
    public double price() {
        return 10.0;
    }

}
