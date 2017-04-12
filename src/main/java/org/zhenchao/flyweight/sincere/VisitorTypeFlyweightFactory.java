package org.zhenchao.flyweight.sincere;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 游客类型处理器享元工厂
 *
 * @author zhenchao.wang 2017-04-09 18:32
 * @version 1.0.0
 */
public class VisitorTypeFlyweightFactory {

    private static final VisitorTypeFlyweightFactory INSTANCE = new VisitorTypeFlyweightFactory();

    private Map<VisitorType, AbstractVisitorTypeFlyweight> flyweightMap = new ConcurrentHashMap<>();

    private VisitorTypeFlyweightFactory() {
    }

    public static VisitorTypeFlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取对应的享元
     *
     * @param visitorType
     * @return
     */
    public AbstractVisitorTypeFlyweight getFlyweight(VisitorType visitorType) {
        if (null == visitorType) {
            return null;
        }

        // 先从缓存中找
        AbstractVisitorTypeFlyweight flyweight = flyweightMap.get(visitorType);
        if (null != flyweight) {
            return flyweight;
        }

        switch (visitorType) {
            case DEFAULT: {
                flyweight = new DefaultVisitorTypeFlyweight();
                flyweightMap.put(VisitorType.DEFAULT, flyweight);
                break;
            }
            case FID: {
                // 设置降级委托处理器
                flyweight = new FidVisitorTypeFlyweight(new DefaultVisitorTypeFlyweight());
                flyweightMap.put(VisitorType.FID, flyweight);
                break;
            }
            case DEVICE: {
                flyweight = new DeviceIdVisitorTypeFlyweight();
                flyweightMap.put(VisitorType.DEVICE, flyweight);
                break;
            }
            default:
                break;
        }
        return flyweight;
    }
}
