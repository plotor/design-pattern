package org.zhenchao.composite.safe;

/**
 * @author zhenchao.wang 2016-11-06 21:37
 * @version 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        Composite root = new Composite("服装");

        Composite man = new Composite("男装");
        man.addComponent(new Leaf("衬衫"));
        man.addComponent(new Leaf("夹克"));
        root.addComponent(man);

        Composite woman = new Composite("女装");
        woman.addComponent(new Leaf("裙子"));
        woman.addComponent(new Leaf("打底裤"));
        root.addComponent(woman);

        root.print("");
    }
}
