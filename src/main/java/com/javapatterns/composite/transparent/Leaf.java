package com.javapatterns.composite.transparent;

import java.util.Enumeration;
import java.util.Vector;

public class Leaf implements Component {
    /**
     * @associates <{Component}>
     * @link aggregation
     * @supplierCardinality 0..*
     */
    private Vector componentVector = new Vector();

    public void sampleOperation() {
        // Write your code here
    }

    public Composite getComposite() {
        // Write your code here
        return null;
    }

    public void add(Component component) {
        componentVector.addElement(component);
    }

    public void remove(Component component) {
        componentVector.removeElement(component);
    }

    public Enumeration components() {
        // Write your code here
        return null;
    }
}
