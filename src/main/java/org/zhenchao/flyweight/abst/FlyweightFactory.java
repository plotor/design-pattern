package org.zhenchao.flyweight.abst;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-09 17:53
 * @version 1.0.0
 */
public class FlyweightFactory {

    private Map<String, AbstractFlyweight> map = new ConcurrentHashMap<>();

    private static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取享元
     *
     * @param key
     * @return
     */
    public AbstractFlyweight getFlyweight(String key) {
        AbstractFlyweight flyweight = map.get(key);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight("some internal state");
            map.put(key, flyweight);
        }
        return flyweight;
    }
}
