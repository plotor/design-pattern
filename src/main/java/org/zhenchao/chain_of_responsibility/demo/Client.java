package org.zhenchao.chain_of_responsibility.demo;

/**
 * @author zhenchao.wang 2016-09-07 23:41
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {

        ConcreteHandler concreteHandler_1 = new ConcreteHandler();
        ConcreteHandler concreteHandler_2 = new ConcreteHandler();

        // 设置后继处理器
        concreteHandler_1.setNextHandler(concreteHandler_2);

        concreteHandler_1.handle();
    }

}
