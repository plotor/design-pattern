package org.zhenchao.flyweight.composite;

/**
 * 组合享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-12 23:52
 * @version 1.0.0
 */
public class CompositeResultHandlerFactory {

    /**基于静态内部类的单例实现*/
    private static class InnerClass {
        private static final CompositeResultHandlerFactory INSTANCE = new CompositeResultHandlerFactory();
    }

    private CompositeResultHandlerFactory() {
    }

    public static CompositeResultHandlerFactory getInstance() {
        return InnerClass.INSTANCE;
    }

}
