package com.javapatterns.liskov.version1;

public class Rectangle {
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
