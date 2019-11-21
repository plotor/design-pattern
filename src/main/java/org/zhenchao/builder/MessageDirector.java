package org.zhenchao.builder;

/**
 * 信息生成指导者
 *
 * @author zhenchao.wang 2016-10-19 23:10
 * @version 1.0.0
 */
public class MessageDirector {

    private MessageBuilder builder;

    public MessageDirector(MessageBuilder builder) {
        this.builder = builder;
    }

    public Message build(
            String receiver, String subject, String body, Object attachment, String sender) {
        builder.setReceiver(receiver);
        builder.setSubject(subject);
        builder.setBody(body);
        builder.setAttachment(attachment);
        builder.setSender(sender);
        return builder.getMessage();
    }

}
