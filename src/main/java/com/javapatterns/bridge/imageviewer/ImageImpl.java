package com.javapatterns.bridge.imageviewer;

abstract public class ImageImpl {
    public long width;
    public long height;
    public byte[] data;

    public abstract void init();

    public abstract void paint();
}
