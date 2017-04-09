package com.javapatterns.decorator.simplified2;

public class ConcreteDecorator implements Component {
    /**
     * @link aggregation
     */
    private Component component;

    public ConcreteDecorator(Component component) {
        super();
        this.component = component;
    }

    public void sampleOperation() {
        component.sampleOperation();
    }
}
