package org.zhenchao.factory.abst;

/**
 * 汽车工厂
 *
 * @author zhenchao.wang 2016-10-08 22:44
 * @version 1.0.0
 */
public interface AbstractCarFactory {

    /**
     * 生产引擎
     *
     * @return
     */
    AbstractEngine buildEngine();

    /**
     * 生产车架
     *
     * @return
     */
    AbstractCarFrame buildFrame();

    /**
     * 生产轮子
     *
     * @return
     */
    AbstractWheel buildWheel();

    /**
     * 组装
     */
    default void assemble() {
        AbstractEngine engine = buildEngine();
        AbstractCarFrame frame = buildFrame();
        AbstractWheel wheel = buildWheel();
        engine.rotating();
        frame.strut();
        wheel.running();
    }

}
