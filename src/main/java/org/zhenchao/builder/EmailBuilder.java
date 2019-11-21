package org.zhenchao.builder;

/**
 * 电子邮件生成器
 *
 * @author zhenchao.wang 2016-10-19 23:06
 * @version 1.0.0
 */
public class EmailBuilder implements MessageBuilder {

    private Message email = new Message();

    @Override
    public void setReceiver(String receiver) {
        this.email.setReceiver(receiver);
    }

    @Override
    public void setSubject(String subject) {
        this.email.setSubject(subject);
    }

    @Override
    public void setBody(String body) {
        this.email.setBody(body);
    }

    @Override
    public void setAttachment(Object attachment) {
        this.email.setAttachment(attachment);
    }

    @Override
    public void setSender(String sender) {
        this.email.setSender(sender);
    }

    @Override
    public Message getMessage() {
        return this.email;
    }
}
