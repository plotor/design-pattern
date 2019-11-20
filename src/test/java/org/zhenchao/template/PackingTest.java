package org.zhenchao.template;

import org.junit.Test;

/**
 * @author zhenchao.wang 2019-11-20 10:23
 * @version 1.0.0
 */
public class PackingTest {

    @Test
    public void putIn() {
        Packing packing = new PackElephant();
        packing.putInTheRefrigerator();

        packing = new PackGrape();
        packing.putInTheRefrigerator();
    }
}