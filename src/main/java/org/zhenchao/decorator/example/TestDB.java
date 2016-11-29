package org.zhenchao.decorator.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenchao.wang 2016-11-29 22:29
 * @version 1.0.0
 */
public class TestDB {

    public static Map<String, Double> saleroom = new HashMap<>();

    private TestDB() {
    }

    static {
        saleroom.put("zhangsan", 10000.0);
        saleroom.put("lisi", 20000.0);
        saleroom.put("wanger", 30000.0);
    }

}
