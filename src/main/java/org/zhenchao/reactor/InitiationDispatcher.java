package org.zhenchao.reactor;

/**
 * @author zhenchao.wang 2017-01-18 09:40
 * @version 1.0.0
 */
public interface InitiationDispatcher {

    void register(EventHandler eventHandler);

    void remove(EventHandler eventHandler);

    void dispatch(EventHandler eventHandler);

}
