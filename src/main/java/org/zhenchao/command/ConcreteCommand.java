package org.zhenchao.command;

/**
 * @author zhenchao.wang 2019-11-30 15:27
 * @version 1.0.0
 */
public class ConcreteCommand implements Command {

    /** 命令接收和执行者 */
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        // 为当前命令绑定一个执行者
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        // 通过调用执行者相应的方法执行命令
        receiver.action();
    }

}
