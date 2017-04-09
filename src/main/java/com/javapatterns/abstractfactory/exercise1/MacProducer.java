package com.javapatterns.abstractfactory.exercise1;

public class MacProducer extends ComputerProducer {
    /**
     * @directed
     */
    private MacCpu lnkMacCPU;
    /**
     * @directed
     */
    private MacRam lnkMacRAM;
    /**
     * @directed
     * @label Creates
     */
    private MacRam lnkMacRAM1;
    /**
     * @label Creates
     * @directed
     */
    private MacCpu lnkMacCPU1;

    public Cpu createCpu() {
        return new MacCpu();
    }

    public Ram createRam() {
        return new MacRam();
    }
}
