package org.zhenchao.bridge.ugly;

/**
 * @author zhenchao.wang 2019-11-21 19:53
 * @version 1.0.0
 */
public class EmailMessage implements Message {

    @Override
    public void send(String message, String to) {
        System.out.println("[电子邮件] 发送消息 [" + message + "] 给 " + to);
    }
}
