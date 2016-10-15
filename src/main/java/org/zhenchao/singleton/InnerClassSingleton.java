package org.zhenchao.singleton;

/**
 * 静态内部类式
 *
 * @author zhenchao.wang 2016-10-15 21:23
 * @version 1.0.0
 */
public class InnerClassSingleton {

    private static class InnerClass {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 私有构造方法
     */
    private InnerClassSingleton() {
    }

    public static InnerClassSingleton getInstance() {
        return InnerClass.INSTANCE;
    }
}
