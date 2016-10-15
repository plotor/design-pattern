package org.zhenchao.singleton;

/**
 * 懒汉式
 * 第一次获取实例时初始化
 *
 * @author zhenchao.wang 2016-10-09 22:39
 * @version 1.0.0
 */
public class LazySingleton {

    private static LazySingleton instance;

    /**
     * 私有的构造方法
     */
    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
