package org.zhenchao.bridge.pretty;

/**
 * @author zhenchao.wang 2019-11-22 19:02
 * @version 1.0.0
 */
public class SpecialUrgencyMessage extends AbstractMessage {

    public SpecialUrgencyMessage(MessageImplementor implementor) {
        super(implementor);
    }

    @Override
    public void sendMessage(String message, String to) {
        String urgencyMessage = "特急：" + message;
        // 发送特急信息
        super.sendMessage(urgencyMessage, to);
    }

    public void hurry(String messageId) {
        // 执行催促逻辑
    }
}
