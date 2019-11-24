package org.zhenchao.composite.safe;

/**
 * 叶子节点
 *
 * @author zhenchao.wang 2016-11-06 21:34
 * @version 1.0.0
 */
public class Leaf extends AbstractComponent {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void print(String parent) {
        System.out.println("\t" + parent + "-" + name);
    }

}
