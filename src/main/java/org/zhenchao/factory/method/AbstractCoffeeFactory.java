package org.zhenchao.factory.method;

import org.zhenchao.factory.simple.Coffee;

/**
 * 咖啡工厂
 *
 * @author zhenchao.wang 2016-10-07 17:21
 * @version 1.0.0
 */
public interface AbstractCoffeeFactory {

    Coffee build();

}
