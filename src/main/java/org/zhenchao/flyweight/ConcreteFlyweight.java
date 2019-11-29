package org.zhenchao.flyweight;

/**
 * 具体享元
 *
 * @author zhenchao.wang 2017-04-09 17:50
 * @version 1.0.0
 */
public class ConcreteFlyweight implements Flyweight {

    /** 内部状态 */
    private Object internalState;

    public ConcreteFlyweight(Object internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operate(Object externalState) {
        // 具体处理逻辑，可能会用到享元的内部、外部状态
    }

}
