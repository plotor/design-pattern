package org.zhenchao.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class MessageV2 implements Product {

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

    public MessageV2(MessageBuilderV2 builder) {
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
