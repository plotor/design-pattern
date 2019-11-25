package org.zhenchao.visitor;

/**
 * @author zhenchao.wang 2019-11-25 12:29
 * @version 1.0.0
 */
public class ConcreteElement2 extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operation() {
        // 已有的功能实现
        System.out.println("concrete element 2 operation");
    }

}
