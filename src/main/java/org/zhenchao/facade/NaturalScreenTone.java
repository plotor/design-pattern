package org.zhenchao.facade;

/**
 * 自然色调
 *
 * @author zhenchao.wang 2017-04-08 23:04
 * @version 1.0.0
 */
public class NaturalScreenTone implements ScreenTone {

    @Override
    public void turnOn() {
        System.out.println("Turn on natural screen tone!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off natural screen tone!");
    }

}
