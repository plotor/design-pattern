package org.zhenchao.immutable;

/**
 * 强不变类
 *
 * @author zhenchao.wang 2017-04-15 22:21
 * @version 1.0.0
 */
public final class ImmutableClass {  // 类应该用final修饰，防止被继承

    /** 所有的属性都应该设置为私有 */

    private Integer a;

    private Double b;

    private String c;

    /**
     * 通过构造方法来实例化对象
     * 如果传入的对象是可变的，则需要复制一份
     *
     * @param a
     * @param b
     * @param c
     */
    public ImmutableClass(Integer a, Double b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /** 所有的方法都需要加 final 修饰 */

    public final Integer getA() {
        return a;
    }

    public final Double getB() {
        return b;
    }

    public final String getC() {
        return c;
    }
}
