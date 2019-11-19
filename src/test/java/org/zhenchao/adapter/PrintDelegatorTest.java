package org.zhenchao.adapter;

import org.junit.Test;

/**
 * @author zhenchao.wang 2019-11-19 18:53
 * @version 1.0.0
 */
public class PrintDelegatorTest {

    @Test
    public void test() {
        Printer printer = new PrintDelegator(new Banner("design-pattern"));
        printer.printStrong();
        printer.printWeak();
    }
}