package com.javapatterns.state;

public class Context {
    /**
     * @link aggregation
     */
    private State state;

    public void sampleOperation() {
        state.sampleOperation();
    }

    public void setState(State state) {
        this.state = state;
    }
}
