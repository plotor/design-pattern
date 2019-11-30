package org.zhenchao.command;

/**
 * @author zhenchao.wang 2019-11-30 15:30
 * @version 1.0.0
 */
public class Invoker {

    /** 持有的命令对象 */
    private Command command;

    /**
     * 发起命令执行请求
     */
    public void runCommand() {
        command.execute();
    }

    public Invoker setCommand(Command command) {
        this.command = command;
        return this;
    }

}
