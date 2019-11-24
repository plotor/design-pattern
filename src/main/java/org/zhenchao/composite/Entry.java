package org.zhenchao.composite;

/**
 * @author zhenchao.wang 2019-11-24 15:02
 * @version 1.0.0
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new UnsupportedOperationException();
    }

    protected abstract void printList(String prefix);

    @Override
    public String toString() {
        return this.getName() + " (" + this.getSize() + ")";
    }
}
