package org.zhenchao.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class MessageV3 implements Product {

    public static class MessageBuilder {
        /** 收件人 */
        private String receiver;

        /** 主题 */
        private String subject;

        /** 正文 */
        private String body;

        /** 附件 */
        private Object attachment;

        /** 发件人 */
        private String addresser;

        public MessageBuilder(String receiver, String body, String addresser) {
            this.receiver = receiver;
            this.body = body;
            this.addresser = addresser;
        }

        /**
         * 设置收信人
         *
         * @param receiver
         * @return
         */
        public MessageBuilder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        /**
         * 设置主题
         *
         * @param subject
         * @return
         */
        public MessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * 设置正文
         *
         * @param body
         * @return
         */
        public MessageBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        /**
         * 设置附件
         *
         * @param attachment
         * @return
         */
        public MessageBuilder setAttachment(Object attachment) {
            this.attachment = attachment;
            return this;
        }

        /**
         * 设置发件人
         *
         * @param addresser
         * @return
         */
        public MessageBuilder setAddresser(String addresser) {
            this.addresser = addresser;
            return this;
        }

        public MessageV3 build() {
            return new MessageV3(this);
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

        public String getAddresser() {
            return addresser;
        }
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
    private String addresser;

    public MessageV3(MessageBuilder builder) {
        this.receiver = builder.getReceiver();
        this.subject = builder.getSubject();
        this.body = builder.getBody();
        this.attachment = builder.getAttachment();
        this.addresser = builder.getAddresser();
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

    public String getAddresser() {
        return addresser;
    }
}
