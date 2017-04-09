package com.javapatterns.command.book;

public class Receiver {

    private int _identification;

    public Receiver(int id) {
        _identification = id;
    }

    public void action() {
        System.out.println(" Action is binded by the Receiver: " + _identification);
    }

}


