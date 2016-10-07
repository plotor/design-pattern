package org.zhenchao.factory.simple;

/**
 * @author zhenchao.wang 2016-10-07 16:19
 * @version 1.0.0
 */
public enum CoffeeEnum {

    LATTE("latte"),
    MOCHA("mocha"),
    CAPPUCCINO("cappuccino");

    private String value;

    CoffeeEnum(String value) {
        this.value = value;
    }
}
