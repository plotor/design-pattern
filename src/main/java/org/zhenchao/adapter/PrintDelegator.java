package org.zhenchao.adapter;

/**
 * @author zhenchao.wang 2019-11-19 18:53
 * @version 1.0.0
 */
public class PrintDelegator implements Printer {

    private Banner banner;

    public PrintDelegator(Banner banner) {
        this.banner = banner;
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
