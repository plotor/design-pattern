package com.javapatterns.composite.exercise1;

import java.util.Enumeration;
import java.util.Vector;

public class Composite implements Component {
    /**
     * @associates <{com.javapatterns.composite.exercise1.Leaf}>
     * @link aggregation
     * @supplierCardinality 0..*
     */
    private Vector componentVector = new Vector();

    public Composite getComposite() {
        return this;
    }

    public void sampleOperation() {
        Enumeration enumeration = components();
        while (enumeration.hasMoreElements()) {
            ((Component) enumeration.nextElement()).sampleOperation();
        }
    }

    public void add(Component component) {
        componentVector.addElement(component);
    }

    public void remove(Component component) {
        componentVector.removeElement(component);
    }

    public Enumeration components() {
        return componentVector.elements();
    }
}
