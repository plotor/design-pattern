package org.zhenchao.builder.v2;

/**
 * 信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public class MessageBuilder {

    private String receiver;
    private String subject;
    private String body;
    private Object attachment;
    private String sender;

    public MessageBuilder() {
    }

    public MessageBuilder(String receiver, String body, String sender) {
        this.receiver = receiver;
        this.body = body;
        this.sender = sender;
    }

    public MessageBuilder setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public MessageBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MessageBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public MessageBuilder setAttachment(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    public MessageBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public Message build() {
        return new Message(this);
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Object getAttachment() {
        return attachment;
    }

    public String getSender() {
        return sender;
    }
}
