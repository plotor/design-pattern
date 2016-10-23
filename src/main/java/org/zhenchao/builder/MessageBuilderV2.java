package org.zhenchao.builder;

/**
 * 信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public class MessageBuilderV2 {

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

    public MessageBuilderV2(String receiver, String body, String addresser) {
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
    public MessageBuilderV2 setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    /**
     * 设置主题
     *
     * @param subject
     * @return
     */
    public MessageBuilderV2 setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * 设置正文
     *
     * @param body
     * @return
     */
    public MessageBuilderV2 setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * 设置附件
     *
     * @param attachment
     * @return
     */
    public MessageBuilderV2 setAttachment(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    /**
     * 设置发件人
     *
     * @param addresser
     * @return
     */
    public MessageBuilderV2 setAddresser(String addresser) {
        this.addresser = addresser;
        return this;
    }

    public MessageV2 build() {
        return new MessageV2(this);
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
