package org.zhenchao.flyweight.sincere;

/**
 * 游客类型处理器抽象享元
 *
 * @author zhenchao.wang 2017-04-09 18:19
 * @version 1.0.0
 */
public abstract class VisitorFlyweight {

    /**
     * 创建或恢复游客信息
     *
     * @param id
     * @return
     */
    public abstract void createOrRecoverVisitor(String id);

}
