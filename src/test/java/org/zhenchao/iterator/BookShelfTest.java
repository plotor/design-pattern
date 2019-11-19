package org.zhenchao.iterator;

import org.junit.Test;

/**
 * @author zhenchao.wang 2019-11-19 17:48
 * @version 1.0.0
 */
public class BookShelfTest {

    @Test
    public void iterator() {
        BookShelf shelf = new BookShelf(10);
        shelf.addBook(new Book("Scala 编程"))
                .addBook(new Book("图解设计模式"))
                .addBook(new Book("Scala 函数式编程"))
                .addBook(new Book("Flink 基础较长"))
                .addBook(new Book("Spark 快速大数据分析"));

        // 基于迭代器遍历
        Iterator itr = shelf.iterator();
        while (itr.hasNext()) {
            Book book = (Book) itr.next();
            System.out.println(book);
        }

    }
}