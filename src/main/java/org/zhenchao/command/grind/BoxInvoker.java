package org.zhenchao.command.grind;

/**
 * @author zhenchao.wang 2019-11-30 15:43
 * @version 1.0.0
 */
public class BoxInvoker {

    private Command bootCommand;

    /**
     * 开机按钮，供用户使用
     */
    public void powerOn() {
        bootCommand.execute();
    }

    public BoxInvoker setBootCommand(Command bootCommand) {
        this.bootCommand = bootCommand;
        return this;
    }

}
