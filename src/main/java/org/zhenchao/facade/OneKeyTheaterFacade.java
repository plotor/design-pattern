package org.zhenchao.facade;

/**
 * 一键影音门面
 *
 * @author zhenchao.wang 2017-04-08 23:08
 * @version 1.0.0
 */
public class OneKeyTheaterFacade {

    private SoundEffect soundEffect;
    private ScreenTone screenTone;

    public OneKeyTheaterFacade() {
        this.soundEffect = new FashionSoundEffect();
        this.screenTone = new GorgeousScreenTone();
    }

    public void turnOn() {
        this.soundEffect.turnOn();
        this.screenTone.turnOn();
    }

    public void turnOff() {
        this.soundEffect.turnOff();
        this.screenTone.turnOff();
    }

}
