package com.javapatterns.iterator.copsandsales;

public class ForwardIterator implements Iterator {

    private int state;
    private ShoppingCart obj;

    public ForwardIterator(ShoppingCart anObj) {
        obj = anObj;
    }

    public void first() {
        state = 0;
    }

    public void next() {
        if (!isDone()) {
            state++;
        }
    }

    public boolean isDone() {
        if (state > obj.count() - 1) {
            return true;
        }
        return false;
    }

    public Object currentItem() {
        return obj.currentItem(state);
    }
}