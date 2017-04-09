package org.zhenchao.flyweight;

/**
 * 默认游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:24
 * @version 1.0.0
 */
public class DefaultVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public DefaultVisitorTypeFlyweight() {
    }

    public DefaultVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String id) {
        System.out.println("Create visitor info in default schema!");
    }

}
