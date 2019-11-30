package org.zhenchao.flyweight.composite;

/**
 * first result handler implementation
 *
 * @author zhenchao.wang 2017-04-12 23:47
 * @version 1.0.0
 */
public class FirstResultHandler extends ResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("first handle");
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY + 40;
    }

}
