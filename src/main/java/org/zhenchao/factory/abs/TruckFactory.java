package org.zhenchao.factory.abs;

/**
 * 卡车制造工厂
 *
 * @author zhenchao.wang 2016-10-09 21:35
 * @version 1.0.0
 */
public class TruckFactory implements AbstractCarFactory {
    @Override
    public AbstractEngine buildEngine() {
        return new TruckEngine();
    }

    @Override
    public AbstractCarFrame buildFrame() {
        return new TruckFrame();
    }

    @Override
    public AbstractWheel buildWheel() {
        return new TruckWheel();
    }
}
