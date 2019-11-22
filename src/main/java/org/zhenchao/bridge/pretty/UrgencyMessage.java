package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 18:54
 * @version 1.0.0
 */
public class UrgencyMessage extends AbstractMessage {

    public UrgencyMessage(MessageImplementor implementor) {
        super(implementor);
    }

    @Override
    public void sendMessage(String message, String to) {
        String urgencyMessage = "加急：" + message;
        // 发送加急信息
        super.sendMessage(urgencyMessage, to);
    }

    public void watch(String messageId) {
        // 执行监控逻辑
    }

}
