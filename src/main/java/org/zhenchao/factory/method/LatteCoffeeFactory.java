package org.zhenchao.factory.method;

import org.zhenchao.factory.simple.Coffee;
import org.zhenchao.factory.simple.Latte;

/**
 * 拿铁咖啡工厂
 *
 * @author zhenchao.wang 2016-10-07 17:25
 * @version 1.0.0
 */
public class LatteCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Latte();
    }
}
