package org.zhenchao.reactor;

/**
 * event handler
 *
 * @author zhenchao.wang 2017-01-18 09:43
 * @version 1.0.0
 */
public interface EventHandler {

    int handle(EventType type);

    Handle getHandle();

}
