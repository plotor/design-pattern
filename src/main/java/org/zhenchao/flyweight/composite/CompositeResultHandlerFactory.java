package org.zhenchao.flyweight.composite;

import org.apache.commons.lang3.StringUtils;

/**
 * 复合享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-12 23:52
 * @version 1.0.0
 */
public class CompositeResultHandlerFactory {

    private ResultHandlerFactory factory = ResultHandlerFactory.getInstance();

    /** 基于静态内部类的单例实现 */
    private static class InnerClass {
        private static final CompositeResultHandlerFactory INSTANCE = new CompositeResultHandlerFactory();
    }

    private CompositeResultHandlerFactory() {
    }

    public static CompositeResultHandlerFactory getInstance() {
        return InnerClass.INSTANCE;
    }

    /**
     * 获取结果处理器复合享元
     *
     * @param key
     * @return
     */
    public CompositeResultHandler getCompositeResultHandler(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 构建复合享元
        CompositeResultHandler compositeHandler = new CompositeResultHandler();
        for (int i = 0; i < key.length(); i++) {
            ResultHandler handler = factory.getResultHandler(String.valueOf(key.charAt(i)));
            if (null != handler) {
                compositeHandler.add(handler);
            }
        }
        return compositeHandler;
    }

}
