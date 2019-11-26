package org.zhenchao.mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:10
 * @version 1.0.0
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        // 需要与其它参与对象进行通信，通知中介者
        this.getMediator().changed(this);
    }

}
