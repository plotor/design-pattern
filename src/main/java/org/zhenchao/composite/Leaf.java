package org.zhenchao.composite;

/**
 * 叶子
 *
 * @author zhenchao.wang 2016-11-06 21:34
 * @version 1.0.0
 */
public class Leaf implements AbstractComponent {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void print(String parent) {
        System.out.println("\t" + parent + "-" + name);
    }

}
