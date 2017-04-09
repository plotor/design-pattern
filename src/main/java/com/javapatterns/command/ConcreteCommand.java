package com.javapatterns.command;

public class ConcreteCommand implements Command {
    /**
     * @directed
     * @clientRole receiver
     */
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }
}
