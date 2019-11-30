package org.zhenchao.flyweight.composite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单纯的结果处理器享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-13 22:09
 * @version 1.0.0
 */
public class ResultHandlerFactory {

    private Map<String, ResultHandler> handlerMap = new ConcurrentHashMap<>(3);

    private static volatile ResultHandlerFactory instance;

    private ResultHandlerFactory() {
    }

    public static ResultHandlerFactory getInstance() {
        if (null == instance) {
            synchronized (ResultHandlerFactory.class) {
                if (null == instance) {
                    instance = new ResultHandlerFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 获取结构处理器享元
     *
     * @param key
     * @return
     */
    public ResultHandler getResultHandler(String key) {
        // 先从缓存中获取
        ResultHandler handler = handlerMap.get(key);
        if (null != handler) {
            return handler;
        }

        // 创建并缓存新的实例
        switch (key) {
            case "1": {
                handler = new FirstResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            case "2": {
                handler = new SecondResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            case "3": {
                handler = new ThirdResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            default:
                throw new IllegalArgumentException("unknown handler key: " + key);
        }
        return handler;
    }

}
