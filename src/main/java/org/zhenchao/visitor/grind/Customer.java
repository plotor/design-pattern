package org.zhenchao.visitor.grind;

/**
 * @author zhenchao.wang 2019-11-25 12:10
 * @version 1.0.0
 */
public abstract class Customer {

    private String id;
    private String name;

    public abstract void accept(Visitor visitor);

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }
}
