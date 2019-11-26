package org.zhenchao.mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:06
 * @version 1.0.0
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        // 需要与其它参与对象进行通信，通知中介者
        this.getMediator().changed(this);
    }

}
