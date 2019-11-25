package org.zhenchao.visitor.grind;

/**
 * @author zhenchao.wang 2019-11-25 14:06
 * @version 1.0.0
 */
public class PreferenceAnalyzeVisitor implements Visitor {

    @Override
    public void visit(PersonalCustomer customer) {
        System.out.println("对个人客户 " + customer.getName() + " 进行产品偏好分析");
    }

    @Override
    public void visit(EnterpriseCustomer customer) {
        System.out.println("对企业客户 " + customer.getName() + " 进行产品偏好分析");
    }

}
