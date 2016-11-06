package org.zhenchao.composite.bad;

/**
 * 叶子节点
 *
 * @author zhenchao.wang 2016-11-06 20:44
 * @version 1.0.0
 */
public class Leaf {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void print(String parent) {
        System.out.println("\t" + parent + "-" + name);
    }

}
