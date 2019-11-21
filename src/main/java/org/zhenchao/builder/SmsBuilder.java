package org.zhenchao.builder;

/**
 * 短信生成器
 *
 * @author zhenchao.wang 2016-10-19 22:58
 * @version 1.0.0
 */
public class SmsBuilder implements MessageBuilder {

    private Message sms = new Message();

    @Override
    public void setReceiver(String receiver) {
        this.sms.setReceiver(receiver);
    }

    @Override
    public void setSubject(String subject) {
        // 短信不需要设置主题
    }

    @Override
    public void setBody(String body) {
        this.sms.setBody(body);
    }

    @Override
    public void setAttachment(Object attachment) {
        // 短信不需要设置附件
    }

    @Override
    public void setSender(String sender) {
        this.sms.setSender(sender);
    }

    @Override
    public Message getMessage() {
        return this.sms;
    }
}
