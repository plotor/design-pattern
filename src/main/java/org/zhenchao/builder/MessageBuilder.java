package org.zhenchao.builder;

/**
 * 抽象信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public interface MessageBuilder {

    void setReceiver(String receiver);

    void setSubject(String subject);

    void setBody(String body);

    void setAttachment(Object attachment);

    void setSender(String sender);

    Message getMessage();

}
