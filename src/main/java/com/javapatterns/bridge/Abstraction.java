package com.javapatterns.bridge;

abstract public class Abstraction {
    /**
     * @link aggregation
     * @directed
     * @supplierRole impl
     */
    private Implementor imp;

    public void operation() {
        imp.operationImp();
    }
}
