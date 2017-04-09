package com.javapatterns.observer.variation;

import java.util.Enumeration;
import java.util.Vector;

abstract public class Subject {

    /**
     * @associates <{Observer}>
     * @link aggregation
     * @supplierCardinality 0..*
     */
    private Vector observersVector = new Vector();

    public void attach(Observer observer) {
        observersVector.addElement(observer);
        System.out.println("Attached an observer.");
    }

    public void detach(Observer observer) {
        observersVector.removeElement(observer);
    }

    public void notifyObservers() {
        Enumeration enumeration = observers();
        while (enumeration.hasMoreElements()) {
            System.out.println("Before notifying");
            ((Observer) enumeration.nextElement()).update();
        }
    }

    public Enumeration observers() {
        return ((Vector) observersVector.clone()).elements();
    }

}
