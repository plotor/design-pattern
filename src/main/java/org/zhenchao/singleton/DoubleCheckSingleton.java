package org.zhenchao.singleton;

/**
 * 双重检查
 *
 * @author zhenchao.wang 2016-10-15 22:07
 * @version 1.0.0
 */
public class DoubleCheckSingleton {

    // 须使用volatile关键字修饰
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

    public DoubleCheckSingleton getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
