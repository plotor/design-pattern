package org.zhenchao.facade;

/**
 * 古典音效
 *
 * @author zhenchao.wang 2017-04-08 23:01
 * @version 1.0.0
 */
public class ClassicalSoundEffect implements SoundEffect {

    @Override
    public void turnOn() {
        System.out.println("Turn on classical sound effect!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn on classical sound effect!");
    }

}
