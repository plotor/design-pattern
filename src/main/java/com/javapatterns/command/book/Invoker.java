package com.javapatterns.command.book;

public class Invoker {

    private Command _command;

    public Invoker(Command c) {
        _command = c;
    }

    public void invoke() {
        if (_command != null) {
            _command.execute();
        }
    }

}
