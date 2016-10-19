package org.zhenchao.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class Message implements Product {

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

    public Message() {
    }

    public Message(String receiver, String body, String addresser) {
        this.receiver = receiver;
        this.body = body;
        this.addresser = addresser;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
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
