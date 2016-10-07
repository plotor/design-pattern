package org.zhenchao.factory.simple;

/**
 * 咖啡抽象类
 *
 * @author zhenchao.wang 2016-10-07 15:59
 * @version 1.0.0
 */
public interface Coffee {

    /**
     * 磨咖啡豆
     */
    default void grind() {
        System.out.println("磨咖啡...");
    }

    /**
     * 煮咖啡
     */
    default void boild() {
        System.out.println("煮咖啡...");
    }

    /**
     * 装杯
     */
    default void pack() {
        System.out.println("装杯...");
    }

}
