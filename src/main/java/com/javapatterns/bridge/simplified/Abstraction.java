package com.javapatterns.bridge.simplified;

abstract public class Abstraction {
    /**
     * @link aggregation
     * @directed
     * @supplierRole imp
     */
    private ConcreteImplementor impl;
    /**
     * @directed
     * @link aggregation
     * @supplierRole impl
     */
    private ConcreteImplementor lnkConcreteImplementorB;

    public void operation() {
        impl.operationImp();
    }
}
