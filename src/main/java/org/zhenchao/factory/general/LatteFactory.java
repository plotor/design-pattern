package org.zhenchao.factory.general;

import org.zhenchao.factory.simple.Coffee;
import org.zhenchao.factory.simple.Latte;

/**
 * @author zhenchao.wang 2016-10-07 17:25
 * @version 1.0.0
 */
public class LatteFactory implements AbstractFactory{
    @Override
    public Coffee build() {
        return new Latte();
    }
}
