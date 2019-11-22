package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 18:52
 * @version 1.0.0
 */
public abstract class AbstractMessage {

    private MessageImplementor implementor;

    public AbstractMessage(MessageImplementor implementor) {
        this.implementor = implementor;
    }

    /**
     * 发送信息
     *
     * @param message
     * @param to
     */
    public void sendMessage(String message, String to) {
        implementor.send(message, to);
    }
}
