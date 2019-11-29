package org.zhenchao.flyweight.sincere;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 游客类型处理器享元工厂
 *
 * @author zhenchao.wang 2017-04-09 18:32
 * @version 1.0.0
 */
public class FlyweightFactory {

    private static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private Map<VisitorType, VisitorFlyweight> flyweightMap = new ConcurrentHashMap<>();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return INSTANCE;
    }

    public VisitorFlyweight getFlyweight(VisitorType visitorType) {
        if (null == visitorType) {
            return null;
        }

        // 先从缓存中找
        VisitorFlyweight flyweight = flyweightMap.get(visitorType);
        if (null != flyweight) {
            return flyweight;
        }

        // 缓存不命中，创建享元并更新缓存
        switch (visitorType) {
            case DEFAULT: {
                flyweight = new DefaultVisitorFlyweight();
                flyweightMap.put(VisitorType.DEFAULT, flyweight);
                break;
            }
            case FID: {
                // 设置降级委托处理器
                flyweight = new FidVisitorFlyweight(new DefaultVisitorFlyweight());
                flyweightMap.put(VisitorType.FID, flyweight);
                break;
            }
            case DEVICE: {
                flyweight = new DeviceIdVisitorFlyweight();
                flyweightMap.put(VisitorType.DEVICE, flyweight);
                break;
            }
            default:
                throw new IllegalArgumentException("unknown visitor type: " + visitorType);
        }
        return flyweight;
    }

}
