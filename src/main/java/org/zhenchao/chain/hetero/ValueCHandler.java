package org.zhenchao.chain.hetero;

import org.zhenchao.chain.model.Result;

/**
 * Value C处理器
 *
 * @author zhenchao.wang 2016-09-11 17:56
 * @version 1.0.0
 */
public class ValueCHandler extends AbstractMethodResultHandler {

    public ValueCHandler() {
    }

    public ValueCHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性C的值
        result.setValC("value c");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}
