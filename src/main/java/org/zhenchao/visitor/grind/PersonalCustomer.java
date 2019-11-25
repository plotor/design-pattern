package org.zhenchao.visitor.grind;

/**
 * 个人客户
 *
 * @author zhenchao.wang 2019-11-25 12:14
 * @version 1.0.0
 */
public class PersonalCustomer extends Customer {

    private String phone;

    private int age;

    private String registerAddress;

    public PersonalCustomer(String name) {
        this.setName(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getPhone() {
        return phone;
    }

    public PersonalCustomer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PersonalCustomer setAge(int age) {
        this.age = age;
        return this;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public PersonalCustomer setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
        return this;
    }
}
