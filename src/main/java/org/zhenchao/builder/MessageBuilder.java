package org.zhenchao.builder;

/**
 * 抽象信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public interface MessageBuilder {

    /**
     * 设置收信人
     */
    void setReceiver(String receiver);

    /**
     * 设置主题
     */
    void setSubject(String subject);

    /**
     * 设置正文
     */
    void setBody(String body);

    /**
     * 设置附件
     */
    void setAttachment(Object attachment);

    /**
     * 设置发件人
     */
    void setAddresser(String addresser);

    /**
     * 获取信息实例
     *
     * @return
     */
    Message retriveMessage();

}
