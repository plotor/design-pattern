package org.zhenchao.flyweight;

import org.apache.commons.lang3.RandomUtils;

/**
 * 游客类型处理器抽象享元
 *
 * @author zhenchao.wang 2017-04-09 18:19
 * @version 1.0.0
 */
public abstract class AbstractVisitorTypeFlyweight {

    /**
     * 创建或恢复游客信息
     *
     * @param id
     * @return
     */
    public abstract void createOrRecoverVisitor(String id);

    /**
     * 内部创建或恢复游客信息
     *
     * @return
     */
    protected boolean createOrRecoverVisitorInternal() {
        // 模拟操作是否成功
        return RandomUtils.nextInt(0, 10) > 5;
    }

}
