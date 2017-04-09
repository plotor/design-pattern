package org.zhenchao.flyweight;

/**
 * DeviceId类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:28
 * @version 1.0.0
 */
public class DeviceIdVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public DeviceIdVisitorTypeFlyweight() {
    }

    public DeviceIdVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String deviceId) {
        System.out.println("Create or recover visitor info by device id : " + deviceId);
    }

}
