package org.zhenchao.template;

/**
 * @author zhenchao.wang 2019-11-20 10:22
 * @version 1.0.0
 */
public class PackGrape extends Packing {

    @Override
    protected void putIn() {
        System.out.println("葡萄是水果，先用果篮装起来，再放入冰箱");
    }

}
