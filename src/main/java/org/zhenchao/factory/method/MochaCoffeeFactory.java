package org.zhenchao.factory.method;

import org.zhenchao.factory.simple.Coffee;
import org.zhenchao.factory.simple.Mocha;

/**
 * 摩卡咖啡工厂
 *
 * @author zhenchao.wang 2016-10-07 17:26
 * @version 1.0.0
 */
public class MochaCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Mocha();
    }
}
