package com.javapatterns.abstractfactory.exercise3;

public class PcProducer extends ComputerProducer {
    private static PcProducer producer = new PcProducer();

    private PcProducer() {
    }

    public static PcProducer getInstance() {
        return producer;
    }

    public Cpu createCpu() {
        return new PcCpu();
    }

    public Ram createRam() {
        return new PcRam();
    }

    /** @link dependency
     * @label Creates*/
    /*# PcCpu lnkPcCpu; */

    /** @link dependency
     * @label Creates*/
    /*# PcRam lnkPcRam; */
}
