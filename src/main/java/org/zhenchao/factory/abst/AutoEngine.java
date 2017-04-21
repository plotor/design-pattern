package org.zhenchao.factory.abst;

/**
 * 汽车引擎
 *
 * @author zhenchao.wang 2016-10-09 21:23
 * @version 1.0.0
 */
public class AutoEngine implements AbstractEngine {
    @Override
    public void rotating() {
        System.out.println("This is auto engine.");
    }
}
