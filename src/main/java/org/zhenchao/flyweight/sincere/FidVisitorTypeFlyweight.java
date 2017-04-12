package org.zhenchao.flyweight.sincere;

/**
 * fid类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:26
 * @version 1.0.0
 */
public class FidVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 内部状态：委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public FidVisitorTypeFlyweight() {
    }

    public FidVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String fid) {
        System.out.println("Create or recover visitor info by fid : " + fid);

        if (null != delegateFlyweight && !this.createOrRecoverVisitorInternal()) {
            // 调用委托处理器处理
            delegateFlyweight.createOrRecoverVisitor(fid);
        }
    }

}
