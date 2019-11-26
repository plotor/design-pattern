package org.zhenchao.mediator.grind;

/**
 * @author zhenchao.wang 2019-11-26 14:26
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        MotherBoard mediator = new MotherBoard();

        CD cd = new CD(mediator);
        CPU cpu = new CPU(mediator);
        VideoCard vc = new VideoCard(mediator);
        SoundCard sc = new SoundCard(mediator);

        mediator.setCd(cd);
        mediator.setCpu(cpu);
        mediator.setVideoCard(vc);
        mediator.setSoundCard(sc);

        cd.read();
    }

}
