package org.zhenchao.adapter;

/**
 * @author zhenchao.wang 2019-11-19 18:46
 * @version 1.0.0
 */
public class Banner {

    private String text;

    public Banner(String text) {
        this.text = text;
    }

    public void showWithParen() {
        System.out.println("(" + text + ")");
    }

    public void showWithAster() {
        System.out.println("*" + text + "*");
    }
}
