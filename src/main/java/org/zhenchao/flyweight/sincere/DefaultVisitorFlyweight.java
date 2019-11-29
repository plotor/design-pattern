package org.zhenchao.flyweight.sincere;

/**
 * 默认游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:24
 * @version 1.0.0
 */
public class DefaultVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public DefaultVisitorFlyweight() {
    }

    public DefaultVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String id) {
        System.out.println("Create visitor info in default schema!");
    }

}
