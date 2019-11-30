package org.zhenchao.command.grind;

/**
 * @author zhenchao.wang 2019-11-30 15:41
 * @version 1.0.0
 */
public class BootCommand implements Command {

    /** 主板对象 */
    private MotherBoard motherBoard;

    public BootCommand(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    @Override
    public void execute() {
        // 调用主板接口执行开机操作
        motherBoard.boot();
    }

}
