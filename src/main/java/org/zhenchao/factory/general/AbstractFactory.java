package org.zhenchao.factory.general;

import org.zhenchao.factory.simple.Coffee;

/**
 * 抽象工厂
 *
 * @author zhenchao.wang 2016-10-07 17:21
 * @version 1.0.0
 */
public interface AbstractFactory {

    Coffee build();

}
