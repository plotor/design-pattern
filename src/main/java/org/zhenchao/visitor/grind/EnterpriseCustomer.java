package org.zhenchao.visitor.grind;

/**
 * 企业客户
 *
 * @author zhenchao.wang 2019-11-25 12:11
 * @version 1.0.0
 */
public class EnterpriseCustomer extends Customer {

    private String linkMan;
    private String linkPhone;
    private String registerAddress;

    public EnterpriseCustomer(String name) {
        this.setName(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getLinkMan() {
        return linkMan;
    }

    public EnterpriseCustomer setLinkMan(String linkMan) {
        this.linkMan = linkMan;
        return this;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public EnterpriseCustomer setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
        return this;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public EnterpriseCustomer setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
        return this;
    }
}
