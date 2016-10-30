package org.zhenchao.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型管理器
 *
 * @author zhenchao.wang 2016-10-30 12:25
 * @version 1.0.0
 */
public class PrototypeManager {

    private List<Animal> animals = new ArrayList<>();

    public void add(Animal animal) {
        animals.add(animal);
    }

    public Animal get(int i) {
        return animals.get(i);
    }

    public int getSize() {
        return animals.size();
    }

}
