package org.zhenchao.visitor;

/**
 * @author zhenchao.wang 2019-11-25 12:30
 * @version 1.0.0
 */
public class ConcreteVisitor implements Visitor {

    @Override
    public void visit(ConcreteElement1 element) {
        element.operation();
    }

    @Override
    public void visit(ConcreteElement2 element) {
        element.operation();
    }

}
