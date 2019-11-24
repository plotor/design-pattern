package org.zhenchao.composite.transparency;

/**
 * 抽象组件
 *
 * @author zhenchao.wang 2016-11-06 21:18
 * @version 1.0.0
 */
public abstract class AbstractComponent {

    public abstract void print(String parent);

    public AbstractComponent get(int index) {
        throw new UnsupportedOperationException();
    }

    public void add(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

}
