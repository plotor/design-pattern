package com.javapatterns.adapter.iterenum;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Enuterator implements Iterator {

    Enumeration enumeration;

    public Enuterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public Object next() throws NoSuchElementException {
        return enumeration.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}
