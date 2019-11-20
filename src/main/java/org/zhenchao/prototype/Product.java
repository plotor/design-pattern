package org.zhenchao.prototype;

/**
 * @author zhenchao.wang 2019-11-20 20:28
 * @version 1.0.0
 */
public abstract class Product implements Cloneable {

    public abstract void use(String s);

    public abstract Product createClone();

}
