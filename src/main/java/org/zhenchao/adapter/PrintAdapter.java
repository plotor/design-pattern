package org.zhenchao.adapter;

/**
 * @author zhenchao.wang 2019-11-19 18:48
 * @version 1.0.0
 */
public class PrintAdapter extends Banner implements Printer {

    public PrintAdapter(String text) {
        super(text);
    }

    @Override
    public void printWeak() {
        super.showWithParen();
    }

    @Override
    public void printStrong() {
        super.showWithAster();
    }
}
