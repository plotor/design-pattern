package org.zhenchao.decorator.coffee;

/**
 * 咖啡抽象
 *
 * @author zhenchao.wang 2016-12-03 12:13
 * @version 1.0.0
 */
public abstract class Coffee {

    private String name;

    public Coffee(String name) {
        this.name = name;
    }

    /**
     * 价格
     *
     * @return
     */
    public abstract double price();

    public String getName() {
        return name;
    }
}
