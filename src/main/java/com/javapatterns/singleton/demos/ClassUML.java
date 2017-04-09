package com.javapatterns.singleton.demos;

/**
 * This is an example of UML Class Chart
 */
public class ClassUML {
    public int aPublicVar;
    protected int aProtectedVar;
    private int aPrivateVar;

    public ClassUML() {
    }

    static public void aStaticMethod() {
    }

    private void aPrivateFunction() {
    }

    public void aPublicMethod() {
    }

    public int getAProperty() {
        return aPrivateVar;
    }

    public void setAProperty(int aPrivateVar) {
        this.aPrivateVar = aPrivateVar;
    }

    protected void aProtectedMethod() {
    }
}
