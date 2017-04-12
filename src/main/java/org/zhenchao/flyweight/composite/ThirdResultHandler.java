package org.zhenchao.flyweight.composite;

/**
 * third result handler implementation
 *
 * @author zhenchao.wang 2017-4-12 23:49:25
 * @version 1.0.0
 */
public class ThirdResultHandler extends AbstractResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("third handle");
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY + 20;
    }

}
