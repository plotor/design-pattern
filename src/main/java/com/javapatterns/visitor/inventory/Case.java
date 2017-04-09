package com.javapatterns.visitor.inventory;

public class Case extends Equipment {
    public void accept(Visitor v) {
        System.out.println("Case has been visited.");
        v.visitCase(this);
    }

    public double price() {
        return 30.00;
    }
}
