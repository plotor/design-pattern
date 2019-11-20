package org.zhenchao.prototype;

/**
 * @author zhenchao.wang 2019-11-20 20:36
 * @version 1.0.0
 */
public class MessageBox extends Product {

    private char decochar;

    public MessageBox(char decochar) {
        this.decochar = decochar;
    }

    @Override
    public void use(String s) {

    }

    @Override
    public Product createClone() {
        return null;
    }
}
