package org.zhenchao.visitor;

/**
 * @author zhenchao.wang 2019-11-25 13:51
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();

        // 创建并添加元素到对象结构中
        os.add(new ConcreteElement1());
        os.add(new ConcreteElement2());

        // 创建访问者
        Visitor visitor = new ConcreteVisitor();

        // 调用业务处理的方法
        os.handleRequest(visitor);
    }

}
