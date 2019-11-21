package com.javapatterns.liskov.version2;

public class Square extends Rectangle {
    private long side;

    public long getSide() {
        return side;
    }

    @Override
    public void setWidth(long width) {
        this.setSide(width);
    }

    public void setSide(long side) {
        this.side = side;
    }

    @Override
    public long getWidth() {
        return this.getSide();
    }

    @Override
    public void setHeight(long height) {
        this.setSide(height);
    }

    @Override
    public long getHeight() {
        return this.getSide();
    }

}