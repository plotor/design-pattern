package org.zhenchao.factory.general;

import org.zhenchao.factory.simple.Cappuccino;
import org.zhenchao.factory.simple.Coffee;

/**
 * @author zhenchao.wang 2016-10-07 17:28
 * @version 1.0.0
 */
public class CappuccinoFactory implements AbstractFactory {
    @Override
    public Coffee build() {
        return new Cappuccino();
    }
}
