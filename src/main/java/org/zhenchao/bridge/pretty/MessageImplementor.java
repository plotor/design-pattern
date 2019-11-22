package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 18:51
 * @version 1.0.0
 */
public interface MessageImplementor {

    /**
     * 发送信息
     *
     * @param message
     * @param to
     */
    void send(String message, String to);

}
