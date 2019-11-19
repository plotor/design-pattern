package org.zhenchao.adapter;

import org.junit.Test;

/**
 * @author zhenchao.wang 2019-11-19 18:50
 * @version 1.0.0
 */
public class PrintAdapterTest {

    @Test
    public void test() {
        PrintAdapter adapter = new PrintAdapter("design-pattern");
        adapter.printStrong();
        adapter.printWeak();
    }
}