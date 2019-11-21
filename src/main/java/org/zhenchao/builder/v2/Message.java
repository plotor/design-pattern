package org.zhenchao.builder.v2;

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

    public Message(MessageBuilder builder) {
        this.receiver = builder.getReceiver();
        this.subject = builder.getSubject();
        this.body = builder.getBody();
        this.attachment = builder.getAttachment();
        this.sender = builder.getSender();
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
