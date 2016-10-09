package org.zhenchao.factory.abs;

/**
 * @author zhenchao.wang 2016-10-09 21:40
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        AbstractCarFactory autoFactory = new AutoFactory();
        autoFactory.assemble();
        System.out.println("-----------------");
        AbstractCarFactory truckFactory = new TruckFactory();
        truckFactory.assemble();
    }

}
