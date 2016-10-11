package org.zhenchao.responsibility.hetero;

import org.zhenchao.responsibility.model.Result;

/**
 * Value A处理器
 *
 * @author zhenchao.wang 2016-09-11 16:55
 * @version 1.0.0
 */
public class ValueAHandler extends AbstractMethodResultHandler {

    public ValueAHandler() {
    }

    public ValueAHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性A的值
        result.setValA("value a");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}