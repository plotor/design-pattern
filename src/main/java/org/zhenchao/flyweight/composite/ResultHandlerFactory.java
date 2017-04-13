package org.zhenchao.flyweight.composite;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单纯的结果处理器享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-13 22:09
 * @version 1.0.0
 */
public class ResultHandlerFactory {

    private Map<String, AbstractResultHandler> handlerMap = new ConcurrentHashMap<>(4);

    private static volatile ResultHandlerFactory instance;

    private ResultHandlerFactory() {
    }

    /**
     * 双检查单例
     *
     * @return
     */
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
    public AbstractResultHandler getResultHandler(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        AbstractResultHandler handler = handlerMap.get(key);
        if (null != handler) {
            return handler;
        }

        switch (key) {
            case "1": {
                handler = new FirstResultHandler();
                handlerMap.put("1", handler);
                break;
            }
            case "2": {
                handler = new SecondResultHandler();
                handlerMap.put("2", handler);
                break;
            }
            case "3": {
                handler = new ThirdResultHandler();
                handlerMap.put("3", handler);
                break;
            }
            case "4": {
                handler = new FourthResultHandler();
                handlerMap.put("4", handler);
                break;
            }
            default:
                // nothing
        }
        return handler;
    }
}
