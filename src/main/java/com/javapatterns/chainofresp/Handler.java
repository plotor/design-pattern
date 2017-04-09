package com.javapatterns.chainofresp;

public abstract class Handler {
    /**
     * @link aggregation
     * @supplierCardinality 0..1
     */
    protected Handler successor;

    public abstract void handleRequest();

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
