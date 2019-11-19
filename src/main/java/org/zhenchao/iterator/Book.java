package org.zhenchao.iterator;

/**
 * 书籍
 *
 * @author zhenchao.wang 2019-11-19 17:24
 * @version 1.0.0
 */
public class Book {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: " + this.name;
    }

    public String getName() {
        return name;
    }
}
