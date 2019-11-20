package org.zhenchao.template;

/**
 * @author zhenchao.wang 2019-11-20 10:18
 * @version 1.0.0
 */
public abstract class Packing {

    /**
     * 模板方法，将物品放入冰箱
     */
    public void putInTheRefrigerator() {
        this.open();
        this.putIn();
        this.close();
    }

    protected void open() {
        System.out.println("打开冰箱门");
    }

    /**
     * 放入物品
     */
    protected abstract void putIn();

    protected void close() {
        System.out.println("关闭冰箱门");
    }

}
