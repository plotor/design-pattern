package org.zhenchao.visitor.graphic;

/**
 * @author zhenchao.wang 2019-11-25 09:30
 * @version 1.0.0
 */
public abstract class Visitor {

    public abstract void visit(File file);

    public abstract void visit(Directory directory);

}
