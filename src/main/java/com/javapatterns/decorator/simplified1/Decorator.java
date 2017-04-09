package com.javapatterns.decorator.simplified1;

public class Decorator extends ConcreteComponent {
    /**
     * @link aggregation
     */
    private ConcreteComponent component;

    public Decorator(ConcreteComponent component) {
//        super();
        this.component = component;
    }

    public Decorator() {
    }

    public void sampleOperation() {
        component.sampleOperation();
    }
}
