package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 19:07
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        // 以站内短消息的形式发送
        MessageImplementor implementor = new SmsMessage();

        // 创建一条普通信息对象
        AbstractMessage message = new CommonMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");

        // 创建一条加急消息
        message = new UrgencyMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");

        // 创建一条特急消息
        message = new SpecialUrgencyMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");

        // 替换实现方式为电子邮件
        implementor = new EmailMessage();

        // 创建一条普通信息对象
        message = new CommonMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");

        // 创建一条加急消息
        message = new UrgencyMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");

        // 创建一条特急消息
        message = new SpecialUrgencyMessage(implementor);
        message.sendMessage("请喝一杯茶", "小李");
    }

}
