package org.zhenchao.facade;

/**
 * 流行音效
 *
 * @author zhenchao.wang 2017-04-08 22:59
 * @version 1.0.0
 */
public class FashionSoundEffect implements AbstractSoundEffect {

    @Override
    public void turnOn() {
        System.out.println("Turn on fashion sound effect!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off fashion sound effect!");
    }

}
