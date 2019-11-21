package org.zhenchao.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器
 *
 * @author zhenchao.wang 2016-10-30 12:25
 * @version 1.0.0
 */
public class PrototypeManager {

    private Map<String, Animal> animals = new HashMap<>();

    public void add(String name, Animal animal) {
        animals.put(name, animal);
    }

    public Animal get(String name) {
        return animals.get(name);
    }

    public int getSize() {
        return animals.size();
    }

}
