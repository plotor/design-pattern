package org.zhenchao.visitor.graphic;

/**
 * @author zhenchao.wang 2019-11-25 09:31
 * @version 1.0.0
 */
public interface Element {

    void accept(Visitor visitor);

}
