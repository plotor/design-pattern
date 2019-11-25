package org.zhenchao.chain.demo;

/**
 * 具象处理者
 *
 * @author zhenchao.wang 2016-09-07 23:36
 * @version 1.0.0
 */
public class ConcreteHandler extends Handler {

    @Override
    public void handle() {
        boolean condition = false;
        if (condition) {
            // do handle
            return;
        }
        // 不再职责范围内，转给后继处理器
        if (null != next) {
            next.handle();
        }
    }

}
