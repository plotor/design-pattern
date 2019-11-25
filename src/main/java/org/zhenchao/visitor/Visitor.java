package org.zhenchao.visitor;

/**
 * @author zhenchao.wang 2019-11-25 12:27
 * @version 1.0.0
 */
public interface Visitor {

    void visit(ConcreteElement1 element);

    void visit(ConcreteElement2 element);

}
