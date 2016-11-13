package org.zhenchao.composite.bad;

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-11-06 21:00
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Composite root = new Composite("服装");

        Composite man = new Composite("男装");
        man.addLeaf(new Leaf("衬衫"));
        man.addLeaf(new Leaf("夹克"));
        root.addComposite(man);

        Composite woman = new Composite("女装");
        woman.addLeaf(new Leaf("裙子"));
        woman.addLeaf(new Leaf("打底裤"));
        root.addComposite(woman);

        root.print("");
    }
}
