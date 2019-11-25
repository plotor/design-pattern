package org.zhenchao.visitor.graphic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhenchao.wang 2019-11-25 09:31
 * @version 1.0.0
 */
public class Directory extends Entry {

    private String name;
    private List<Entry> entries = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return (int) entries.stream().map(Entry::getSize).count();
    }

    @Override
    public Entry add(Entry entry) {
        entries.add(entry);
        return this;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Iterator iterator() {
        return entries.iterator();
    }
}
