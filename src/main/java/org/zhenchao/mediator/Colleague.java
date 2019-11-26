package org.zhenchao.mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:05
 * @version 1.0.0
 */
public abstract class Colleague {

    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

}
