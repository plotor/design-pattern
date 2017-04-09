package com.javapatterns.strategy;

public class Context {
    /**
     * @link aggregation
     * @directed
     */
    private Strategy strategy;

    public void contextInterface() {
        strategy.strategyInterface();
    }
}
