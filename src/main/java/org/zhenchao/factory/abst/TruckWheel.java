package org.zhenchao.factory.abst;

/**
 * 卡车轮胎
 *
 * @author zhenchao.wang 2016-10-09 21:29
 * @version 1.0.0
 */
public class TruckWheel implements AbstractWheel {
    @Override
    public void running() {
        System.out.println("This is truck wheel.");
    }
}
