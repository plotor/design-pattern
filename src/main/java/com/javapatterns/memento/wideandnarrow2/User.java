package com.javapatterns.memento.wideandnarrow2;

public class User {
    public Narrow getConcreteClass() {
        return (Narrow) new ConcreteClass();
    }

    class ConcreteClass implements Narrow {
        private void operation1() {
            System.out.println("operation1()");
        }

        private void operation2() {
            System.out.println("operation2()");
        }
    }
}
