package org.zhenchao.singleton;

/**
 * 饿汉式
 * 类被加载时实例化
 *
 * @author zhenchao.wang 2016-10-09 22:30
 * @version 1.0.0
 */
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造函数
     */
    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

}
