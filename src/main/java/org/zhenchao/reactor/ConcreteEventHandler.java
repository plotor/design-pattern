package org.zhenchao.reactor;

/**
 * @author zhenchao.wang 2017-01-18 09:45
 * @version 1.0.0
 */
public class ConcreteEventHandler implements EventHandler {
    @Override
    public int handle(EventType type) {
        return 0;
    }

    @Override
    public Handle getHandle() {
        return null;
    }
}
