package org.zhenchao.factory.abst;

/**
 * 卡车引擎
 *
 * @author zhenchao.wang 2016-10-09 21:23
 * @version 1.0.0
 */
public class TruckEngine implements AbstractEngine {
    @Override
    public void rotating() {
        System.out.println("This is truck engine.");
    }
}
