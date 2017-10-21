package org.zhenchao.reactor.theory;

/**
 * initiation dispatcher
 *
 * @author zhenchao.wang 2017-01-18 09:40
 * @version 1.0.0
 */
public class InitiationDispatcher {

    int registerHandler(EventHandler handler, EventType type) {
        return -1;
    }

    int removeHandler(EventHandler handler, EventType type) {
        return -1;
    }

    int handleEvents(long timeout) {
        return -1;
    }

}
