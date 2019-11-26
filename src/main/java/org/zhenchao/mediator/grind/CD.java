package org.zhenchao.mediator.grind;

import org.zhenchao.mediator.Colleague;
import org.zhenchao.mediator.Mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:13
 * @version 1.0.0
 */
public class CD extends Colleague {

    private String data = "";

    public CD(Mediator mediator) {
        super(mediator);
    }

    public void read() {
        this.data = "design-pattern, the art of programming";
        // 读取数据成功，通知主板
        this.getMediator().changed(this);
    }

    public String getData() {
        return data;
    }

}
