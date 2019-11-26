package org.zhenchao.mediator.grind;

import org.zhenchao.mediator.Colleague;
import org.zhenchao.mediator.Mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:21
 * @version 1.0.0
 */
public class MotherBoard implements Mediator {

    private CD cd;
    private CPU cpu;
    private VideoCard videoCard;
    private SoundCard soundCard;

    @Override
    public void changed(Colleague colleague) {
        if (colleague == cd) {
            this.processData(cd);
        } else if (colleague == cpu) {
            this.processData(cpu);
        } else {
            throw new IllegalStateException("unknown colleague: " + colleague);
        }
    }

    private void processData(CD cd) {
        // 从光驱读取数据
        String data = cd.getData();
        // 将数据交给 CPU 处理
        cpu.execute(data);
    }

    private void processData(CPU cpu) {
        // 获取 CPU 处理后的数据
        String videoData = cpu.getVideoData();
        String soundData = cpu.getSoundData();
        // 将数据分别传递给显卡和声卡
        videoCard.display(videoData);
        soundCard.display(soundData);
    }

    public MotherBoard setCd(CD cd) {
        this.cd = cd;
        return this;
    }

    public MotherBoard setCpu(CPU cpu) {
        this.cpu = cpu;
        return this;
    }

    public MotherBoard setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
        return this;
    }

    public MotherBoard setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
        return this;
    }
}
