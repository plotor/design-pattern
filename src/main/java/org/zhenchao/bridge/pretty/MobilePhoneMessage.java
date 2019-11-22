package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 19:05
 * @version 1.0.0
 */
public class MobilePhoneMessage implements MessageImplementor {

    @Override
    public void send(String message, String to) {
        System.out.println("[手机短消息] 发送信息 [" + message + "] 给 " + to);
    }
}
