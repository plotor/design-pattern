package org.zhenchao.mediator.grind;

import org.zhenchao.mediator.Colleague;
import org.zhenchao.mediator.Mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:16
 * @version 1.0.0
 */
public class CPU extends Colleague {

    private String videoData;
    private String soundData;

    public CPU(Mediator mediator) {
        super(mediator);
    }

    public void execute(String data) {
        String[] items = data.split(",\\s*");
        this.videoData = items[0];
        this.soundData = items[1];
        // 通知主板，处理数据完成
        this.getMediator().changed(this);
    }

    public String getVideoData() {
        return videoData;
    }

    public String getSoundData() {
        return soundData;
    }

}
