package org.zhenchao.flyweight.sincere;

/**
 * DeviceId类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:28
 * @version 1.0.0
 */
public class DeviceIdVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public DeviceIdVisitorFlyweight() {
    }

    public DeviceIdVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String deviceId) {
        System.out.println("Create or recover visitor info by device id : " + deviceId);
    }

}
