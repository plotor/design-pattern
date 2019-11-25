package org.zhenchao.visitor;

/**
 * @author zhenchao.wang 2019-11-25 12:27
 * @version 1.0.0
 */
public abstract class Element {

    public abstract void accept(Visitor visitor);

}
