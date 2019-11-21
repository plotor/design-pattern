package org.zhenchao.builder.v3;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.zhenchao.builder.Product;

/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class Message implements Product {

    public static class MessageBuilder {

        private String receiver;
        private String subject;
        private String body;
        private Object attachment;
        private String sender;

        private MessageBuilder() {
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

    }

    public static MessageBuilder newBuilder() {
        return new MessageBuilder();
    }

    /** 收件人 */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 正文 */
    private String body;

    /** 附件 */
    private Object attachment;

    /** 发件人 */
    private String sender;

    private Message(MessageBuilder builder) {
        this.receiver = builder.receiver;
        this.subject = builder.subject;
        this.body = builder.body;
        this.attachment = builder.attachment;
        this.sender = builder.sender;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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
