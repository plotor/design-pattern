package org.zhenchao.bridge;

/**
 * @author zhenchao.wang 2019-11-21 20:55
 * @version 1.0.0
 */
public abstract class Abstraction {

    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation() {
        implementor.operationImpl();
    }
}
