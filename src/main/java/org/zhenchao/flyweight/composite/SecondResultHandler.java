package org.zhenchao.flyweight.composite;

/**
 * second result handler implementation
 *
 * @author zhenchao.wang 2017-4-12 23:48:56
 * @version 1.0.0
 */
public class SecondResultHandler extends ResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("second handle");
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY + 30;
    }

}
