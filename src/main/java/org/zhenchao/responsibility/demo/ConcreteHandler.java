package org.zhenchao.responsibility.demo;

/**
 * 具象处理者
 *
 * @author zhenchao.wang 2016-09-07 23:36
 * @version 1.0.0
 */
public class ConcreteHandler extends AbstractHandler {

    public void handle() {

        AbstractHandler nextHandler = getNextHandler();
        if (null != nextHandler) {
            nextHandler.handle();
        } else {
            // handle here
        }

    }
}
