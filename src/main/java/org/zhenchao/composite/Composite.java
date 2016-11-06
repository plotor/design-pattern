package org.zhenchao.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenchao.wang 2016-11-06 21:26
 * @version 1.0.0
 */
public class Composite implements AbstractComponent {

    private String name;

    private List<AbstractComponent> components = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void print(String parent) {
        System.out.println(parent + "-" + name);
        for (final AbstractComponent component : components) {
            component.print("\t");
        }
    }

    @Override
    public void addComponent(AbstractComponent component) {
        this.components.add(component);
    }

    @Override
    public void removeComponent(AbstractComponent component) {
        this.components.remove(component);
    }

    @Override
    public AbstractComponent getComponent(int index) {
        return index >= this.components.size() ? null : this.components.get(index);
    }

}
