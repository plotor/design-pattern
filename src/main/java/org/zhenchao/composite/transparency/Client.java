package org.zhenchao.composite.transparency;

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-11-06 21:37
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        AbstractComponent root = new Composite("服装");

        AbstractComponent man = new Composite("男装");
        man.add(new Leaf("衬衫"));
        man.add(new Leaf("夹克"));
        root.add(man);

        AbstractComponent woman = new Composite("女装");
        woman.add(new Leaf("裙子"));
        woman.add(new Leaf("打底裤"));
        root.add(woman);

        root.print("");
    }
}
