package org.zhenchao.command;

/**
 * @author zhenchao.wang 2019-11-30 15:26
 * @version 1.0.0
 */
public interface Command {

    /**
     * 执行命令对应的操作，命令本身不负责执行，而是调用 Receiver 执行
     */
    void execute();

}
