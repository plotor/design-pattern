package org.zhenchao.flyweight.composite;

/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-13 22:45
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        CompositeResultHandlerFactory factory = CompositeResultHandlerFactory.getInstance();
        AbstractResultHandler handler = factory.getCompositeResultHandler("124");
        handler.handle(new Object());
    }

}
