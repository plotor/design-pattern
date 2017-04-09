package org.zhenchao.flyweight.abst;

/**
 * 具体享元
 *
 * @author zhenchao.wang 2017-04-09 17:50
 * @version 1.0.0
 */
public class ConcreteFlyweight implements AbstractFlyweight {

    private Object internalState;

    public ConcreteFlyweight(Object internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operate(Object externalState) {

    }

}
