package org.zhenchao.factory.abs;

/**
 * @author zhenchao.wang 2016-10-09 21:33
 * @version 1.0.0
 */
public class AutoFactory implements AbstractCarFactory {
    @Override
    public AbstractEngine buildEngine() {
        return new AutoEngine();
    }

    @Override
    public AbstractCarFrame buildFrame() {
        return new AutoFrame();
    }

    @Override
    public AbstractWheel buildWheel() {
        return new AutoWheel();
    }
}
