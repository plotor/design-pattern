package org.zhenchao.bridge.ugly;

/**
 * @author zhenchao.wang 2019-11-21 19:42
 * @version 1.0.0
 */
public class SmsMessage implements Message {

    @Override
    public void send(String message, String to) {
        System.out.println("[站内短消息] 发送消息 [" + message + "] 给 " + to);
    }

}
