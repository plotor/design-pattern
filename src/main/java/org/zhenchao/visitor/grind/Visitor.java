package org.zhenchao.visitor.grind;

/**
 * @author zhenchao.wang 2019-11-25 14:01
 * @version 1.0.0
 */
public interface Visitor {

    void visit(PersonalCustomer customer);

    void visit(EnterpriseCustomer customer);

}
