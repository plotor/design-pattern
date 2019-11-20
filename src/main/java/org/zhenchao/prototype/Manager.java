package org.zhenchao.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenchao.wang 2019-11-20 20:32
 * @version 1.0.0
 */
public class Manager {

    private Map<String, Product> showcase = new HashMap<>();

    public void register(String name, Product product) {
        showcase.put(name, product);
    }

    public Product create(String name) {
        return showcase.get(name).createClone();
    }

}
