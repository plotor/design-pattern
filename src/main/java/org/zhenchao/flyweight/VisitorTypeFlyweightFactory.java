package org.zhenchao.flyweight;

/**
 * 游客类型处理器享元工厂
 *
 * @author zhenchao.wang 2017-04-09 18:32
 * @version 1.0.0
 */
public class VisitorTypeFlyweightFactory {

    private static final VisitorTypeFlyweightFactory INSTANCE = new VisitorTypeFlyweightFactory();

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

        switch (visitorType) {
            case DEFAULT:
                return new DefaultVisitorTypeFlyweight();
            case FID:
                return new FidVisitorTypeFlyweight(new DefaultVisitorTypeFlyweight());
            case DEVICE:
                return new DefaultVisitorTypeFlyweight();
            default:
                return null;
        }
    }
}
