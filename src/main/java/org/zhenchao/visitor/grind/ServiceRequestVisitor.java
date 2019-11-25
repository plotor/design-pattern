package org.zhenchao.visitor.grind;

/**
 * @author zhenchao.wang 2019-11-25 14:03
 * @version 1.0.0
 */
public class ServiceRequestVisitor implements Visitor {

    @Override
    public void visit(PersonalCustomer customer) {
        System.out.println("处理客户 " + customer.getName() + " 提出服务要求");
    }

    @Override
    public void visit(EnterpriseCustomer customer) {
        System.out.println("处理 " + customer.getName() + " 企业提出服务要求");
    }
}
