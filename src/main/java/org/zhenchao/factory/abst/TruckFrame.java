package org.zhenchao.factory.abst;

/**
 * 卡车车架
 *
 * @author zhenchao.wang 2016-10-09 21:26
 * @version 1.0.0
 */
public class TruckFrame implements AbstractCarFrame {
    @Override
    public void strut() {
        System.out.println("This is truck frame.");
    }
}
