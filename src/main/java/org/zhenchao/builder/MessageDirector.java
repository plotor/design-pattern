package org.zhenchao.builder;

/**
 * 信息生成指导者
 *
 * @author zhenchao.wang 2016-10-19 23:10
 * @version 1.0.0
 */
public class MessageDirector {

    private MessageBuilder messageBuilder;

    public MessageDirector(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    /**
     * 指导生成
     *
     * @param receiver
     * @param subject
     * @param body
     * @param attachment
     * @param addresser
     * @return
     */
    public Message build(String receiver, String subject, String body, Object attachment, String addresser) {
        messageBuilder.setReceiver(receiver);
        messageBuilder.setSubject(subject);
        messageBuilder.setBody(body);
        messageBuilder.setAttachment(attachment);
        messageBuilder.setAddresser(addresser);
        return messageBuilder.retriveMessage();
    }
}
