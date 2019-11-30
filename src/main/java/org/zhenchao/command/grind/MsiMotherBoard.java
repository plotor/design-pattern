package org.zhenchao.command.grind;

/**
 * @author zhenchao.wang 2019-11-30 15:38
 * @version 1.0.0
 */
public class MsiMotherBoard implements MotherBoard {

    @Override
    public void boot() {
        System.out.println("欢迎使用微星主板，请稍后...");
        System.out.println("硬件自检中");
        System.out.println("加载操作系统");
        System.out.println("启动成功，欢迎使用");
    }

}
