package org.zhenchao.factory.general;

import org.zhenchao.factory.simple.Coffee;
import org.zhenchao.factory.simple.Mocha;

/**
 * @author zhenchao.wang 2016-10-07 17:26
 * @version 1.0.0
 */
public class MochaFactory implements AbstractFactory {
    @Override
    public Coffee build() {
        return new Mocha();
    }
}
