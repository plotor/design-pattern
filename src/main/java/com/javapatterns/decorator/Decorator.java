package com.javapatterns.decorator;

public class Decorator implements Component {
    /**
     * @link aggregation
     */
    private Component component;

    public Decorator(Component component) {
//        super();
        this.component = component;
    }

    public Decorator() {
    }

    public void sampleOperation() {
        component.sampleOperation();
    }
}
