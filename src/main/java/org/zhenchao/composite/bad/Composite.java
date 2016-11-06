package org.zhenchao.composite.bad;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件
 *
 * @author zhenchao.wang 2016-11-06 20:48
 * @version 1.0.0
 */
public class Composite {

    private List<Composite> childComposites = new ArrayList<>();

    private List<Leaf> leafs = new ArrayList<>();

    private String name;

    public Composite(String name) {
        this.name = name;
    }

    public void addComposite(Composite composite) {
        childComposites.add(composite);
    }

    public void addLeaf(Leaf leaf) {
        this.leafs.add(leaf);
    }

    public void print(String parent) {
        System.out.println(parent + "-" + name);
        for (final Composite childComposite : childComposites) {
            childComposite.print("\t");
        }
        for (final Leaf leaf : leafs) {
            leaf.print("\t");
        }
    }
}
