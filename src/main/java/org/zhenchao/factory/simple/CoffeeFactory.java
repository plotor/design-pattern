package org.zhenchao.factory.simple;

/**
 * 咖啡工场
 *
 * @author zhenchao.wang 2016-10-07 16:21
 * @version 1.0.0
 */
public class CoffeeFactory {

    /**
     * 静态工厂方法
     *
     * @param coffee
     * @return
     */
    public static Coffee build(CoffeeEnum coffee) {
        if (CoffeeEnum.LATTE.equals(coffee)) {
            return new Latte();
        } else if (CoffeeEnum.MOCHA.equals(coffee)) {
            return new Mocha();
        } else if (CoffeeEnum.CAPPUCCINO.equals(coffee)) {
            return new Cappuccino();
        } else {
            throw new IllegalArgumentException("Unknown coffee type!");
        }
    }

}
