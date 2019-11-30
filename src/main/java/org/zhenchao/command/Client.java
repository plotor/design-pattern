package org.zhenchao.command;

/**
 * @author zhenchao.wang 2019-11-30 15:32
 * @version 1.0.0
 */
public class Client {

    public void assemble() {
        // 创建命令执行者
        Receiver receiver = new Receiver();
        // 创建命令对象，并设置其执行者
        Command command = new ConcreteCommand(receiver);
        // 创建 Invoker，并设置命令
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
    }

}
