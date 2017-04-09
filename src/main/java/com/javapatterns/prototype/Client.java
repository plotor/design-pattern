package com.javapatterns.prototype;

public class Client {
    /**
     * @directed
     * @clientRole prototype
     * @link aggregation
     * @clientCardinality 1
     * @supplierCardinality 1..*
     */
    private Prototype prototype;

    public void operation(Prototype example) {
        Prototype p = (Prototype) example.clone();
    }
}
