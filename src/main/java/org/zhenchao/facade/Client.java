package org.zhenchao.facade;

/**
 * 客户端调用
 *
 * @author zhenchao.wang 2017-04-08 23:12
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        // 原始调用方式
        AbstractSoundEffect soundEffect = new FashionSoundEffect();
        soundEffect.turnOn();
        AbstractScreenTone screenTone = new GorgeousScreenTone();
        screenTone.turnOn();

        // 采用门面模式
        OneKeyTheaterFacade facade = new OneKeyTheaterFacade();
        facade.turnOn();
    }

}
