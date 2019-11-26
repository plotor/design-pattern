package org.zhenchao.mediator.grind;

import org.zhenchao.mediator.Colleague;
import org.zhenchao.mediator.Mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:18
 * @version 1.0.0
 */
public class VideoCard extends Colleague {

    public VideoCard(Mediator mediator) {
        super(mediator);
    }

    public void display(String data) {
        System.out.println("播放视频： " + data);
    }

}
