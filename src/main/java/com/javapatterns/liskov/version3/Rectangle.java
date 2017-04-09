package com.javapatterns.liskov.version3;

public class Rectangle implements Quadrangle {
    private long width;
    private long height;

    public long getWidth() {
        return this.width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return this.height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
