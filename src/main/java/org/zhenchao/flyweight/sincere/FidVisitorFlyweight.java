package org.zhenchao.flyweight.sincere;

import org.apache.commons.lang3.RandomUtils;

/**
 * fid类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:26
 * @version 1.0.0
 */
public class FidVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public FidVisitorFlyweight() {
    }

    public FidVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String fid) {
        System.out.println("Create or recover visitor info by fid : " + fid);

        if (null != delegateFlyweight && !this.mockCreateOrRecoverVisitor()) {
            // 调用委托处理器处理
            delegateFlyweight.createOrRecoverVisitor(fid);
        }
    }

    private boolean mockCreateOrRecoverVisitor() {
        // 模拟操作是否成功
        return RandomUtils.nextInt(0, 10) > 5;
    }

}
