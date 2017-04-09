package com.javapatterns.command.undoconcept;

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

    public void unexecute() {
    }

    public void reexecute() {
    }
}
