package org.zhenchao.template;

/**
 * @author zhenchao.wang 2019-11-20 10:21
 * @version 1.0.0
 */
public class PackElephant extends Packing {

    @Override
    protected void putIn() {
        System.out.println("大象较大，先将其切成小块，再放入冰箱");
    }

}
