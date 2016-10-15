package org.zhenchao.factory.general;

import org.zhenchao.factory.simple.Cappuccino;
import org.zhenchao.factory.simple.Coffee;

/**
 * 卡布奇洛咖啡工厂
 *
 * @author zhenchao.wang 2016-10-07 17:28
 * @version 1.0.0
 */
public class CappuccinoCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Cappuccino();
    }
}
