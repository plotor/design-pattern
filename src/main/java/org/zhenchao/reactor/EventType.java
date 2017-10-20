package org.zhenchao.reactor;

/**
 * event type
 *
 * @author zhenchao.wang 2017-01-18 17:26
 * @version 1.0.0
 */
public enum EventType {

    ACCEPT(1),
    READ(2),
    WRITE(4),
    TIMEOUT(10),
    SIGNAL(20),
    CLOSE(40);

    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
