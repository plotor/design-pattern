package org.zhenchao.command.grind;

/**
 * @author zhenchao.wang 2019-11-30 15:45
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        // 组合命令和执行者
        MotherBoard motherBoard = new MsiMotherBoard();
        Command bootCommand = new BootCommand(motherBoard);

        // 为机箱上的按钮设置命令
        BoxInvoker box = new BoxInvoker();
        box.setBootCommand(bootCommand);

        // 按下开机键开机
        box.powerOn();
    }

}
