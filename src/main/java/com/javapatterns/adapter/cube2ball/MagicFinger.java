package com.javapatterns.adapter.cube2ball;

public class MagicFinger implements BallIF {
    private static final double PI = 3.14;
    private double radius = 0;
    private Cube adaptee;

    public MagicFinger(Cube adaptee) {
        super();
        this.adaptee = adaptee;

        radius = adaptee.getWidth();
    }

    /**
     * Class Adaptee does not contain operation calculateSurface.
     */
    public double calculateArea() {
        return PI * 4.0D * (radius * radius);
    }

    /**
     * Class Adaptee contains operation calculateVolume, but
     * we need to override it.
     */
    public double calculateVolume() {
        return PI * 4.0D / 3.0D * (radius * radius * radius);
    }

    /**
     * Class Adaptee does not contain operation getRadius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Class Adaptee does not contain operation getRadius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
