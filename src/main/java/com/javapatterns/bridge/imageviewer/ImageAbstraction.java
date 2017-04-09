package com.javapatterns.bridge.imageviewer;

abstract public class ImageAbstraction {
    /**
     * @link aggregation
     * @directed
     * @supplierRole imp
     */
    protected ImageImpl imp;

    public abstract void load();

    public abstract void show();
}
