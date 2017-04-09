package com.javapatterns.chainofresp.flowerpassingthread;

abstract class Player {
    /**
     * @link aggregation
     */
    private Player successor;

    public Player() {
        successor = null;
    }

    abstract public void handle();

    protected void setSuccessor(Player aSuccessor) {
        successor = aSuccessor;
    }

    public void next() {
        if (successor != null) {
            successor.handle();
        } else {
            System.out.println("Program terminated.");
        }
    }

}

