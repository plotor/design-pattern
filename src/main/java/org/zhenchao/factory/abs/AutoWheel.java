package org.zhenchao.factory.abs;

/**
 * 汽车轮胎
 *
 * @author zhenchao.wang 2016-10-09 21:29
 * @version 1.0.0
 */
public class AutoWheel implements AbstractWheel {
    @Override
    public void running() {
        System.out.println("This is auto wheel.");
    }
}
