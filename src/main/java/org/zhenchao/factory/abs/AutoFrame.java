package org.zhenchao.factory.abs;

/**
 * 汽车车架
 *
 * @author zhenchao.wang 2016-10-09 21:26
 * @version 1.0.0
 */
public class AutoFrame implements AbstractCarFrame {
    @Override
    public void strut() {
        System.out.println("This is auto frame.");
    }
}
