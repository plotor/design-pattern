package com.javapatterns.command.document;

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
