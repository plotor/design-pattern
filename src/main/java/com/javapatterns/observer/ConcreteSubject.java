package com.javapatterns.observer;

import java.util.Enumeration;
import java.util.Vector;

public class ConcreteSubject implements Subject {
    /**
     * @associates <{Observer}>
     * @link aggregation
     * @supplierCardinality 0..*
     */
    private Vector observersVector = new Vector();

    public void attach(Observer observer) {
        observersVector.addElement(observer);
    }

    public void detach(Observer observer) {
        observersVector.removeElement(observer);
    }

    public void notifyObservers() {
        Enumeration enumeration = observers();
        while (enumeration.hasMoreElements()) {
            ((Observer) enumeration.nextElement()).update();
        }
    }

    public Enumeration observers() {
        return ((Vector) observersVector.clone()).elements();
    }
}
