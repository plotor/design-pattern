package com.javapatterns.liskov.version4;

public class ImmutableSquare extends Rectangle {
    private long side;

    public long getWidth() {
        return getSide();
    }

    public void setWidth(long width) {
//        setSide(width);
    }

    public long getHeight() {
        return getSide();
    }

    public void setHeight(long height) {
//        setSide(height);
    }

    public long getSide() {
        return side;
    }

    public void setSide(long side) {
//        this.side = side;
    }
}