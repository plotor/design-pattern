package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 18:52
 * @version 1.0.0
 */
public class EmailMessage implements MessageImplementor {

    @Override
    public void send(String message, String to) {
        System.out.println("[电子邮件] 发送信息 [" + message + "] 给 [" + to + "]");
    }

}
