package org.zhenchao.visitor.graphic;

import java.util.Iterator;

/**
 * @author zhenchao.wang 2019-11-25 09:33
 * @version 1.0.0
 */
public abstract class Entry implements Element {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new UnsupportedOperationException();
    }

    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getSize() + ")";
    }
}
