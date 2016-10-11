package org.zhenchao.responsibility.hetero;

import org.zhenchao.responsibility.model.Result;

/**
 * Value B处理器
 *
 * @author zhenchao.wang 2016-09-11 16:56
 * @version 1.0.0
 */
public class ValueBHandler extends AbstractMethodResultHandler {

    public ValueBHandler() {
    }

    public ValueBHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性B的值
        result.setValB("value b");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}
