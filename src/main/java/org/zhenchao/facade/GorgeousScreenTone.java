package org.zhenchao.facade;

/**
 * 艳丽色调
 *
 * @author zhenchao.wang 2017-04-08 23:06
 * @version 1.0.0
 */
public class GorgeousScreenTone implements ScreenTone {

    @Override
    public void turnOn() {
        System.out.println("Turn on gorgeous screen tone!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off gorgeous screen tone!");
    }

}
