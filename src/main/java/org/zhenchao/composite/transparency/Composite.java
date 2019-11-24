package org.zhenchao.composite.transparency;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝节点
 *
 * @author zhenchao.wang 2016-11-06 21:26
 * @version 1.0.0
 */
public class Composite extends AbstractComponent {

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
    public AbstractComponent get(int index) {
        return index >= this.components.size() ? null : this.components.get(index);
    }

    @Override
    public void add(AbstractComponent component) {
        this.components.add(component);
    }

    @Override
    public void remove(AbstractComponent component) {
        this.components.remove(component);
    }

}
