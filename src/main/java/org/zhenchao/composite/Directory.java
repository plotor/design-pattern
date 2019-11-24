package org.zhenchao.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenchao.wang 2019-11-24 15:08
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
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this.toString());
        entries.forEach(entry -> entry.printList(prefix));
    }
}
