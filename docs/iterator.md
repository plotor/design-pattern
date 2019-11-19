### 迭代器模式

迭代器（Iterator）模式的作用在于提供一种方法顺序访问一个集合对象中的各个元素，而又不暴露其内部的实现。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
迭代器 | Iterator | 接口 | 定义按顺序逐个遍历元素的接口，声明 `hasNext()` 和 `next()` 两个方法，其中前者用于判断是否存在下一个元素，后者用于获取元素，并推动迭代器指针；还有一些 Iterator 的定义中会加入 `remove()` 方法，比如 `java.util.Iterator`，以实现在遍历集合的过程中对元素进行删除
具体迭代器 | ConcreteIterator | 类 | 实现自 Iterator 接口
集合 | Aggregate | 接口 | 为具体实现类附加迭代特性，声明了一个 `iterator()` 方法，用于返回一个新创建的迭代器对象
具体集合 | ConcreteAggregate | 类 | 实现自 Aggregate 接口

#### 示例

假设我们需要为一个书架 BookShelf 附加一个遍历书架上书籍的功能，核心接口 Iterator 和 Aggregate 定义如下：

```java
/**
 * 迭代器接口
 *
 * @author zhenchao.wang 2019-11-19 17:26
 * @version 1.0.0
 */
public interface Iterator {

    /**
     * 是否存在下一个元素
     *
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个元素，同时会将迭代器移动到下一个元素
     *
     * @return
     */
    Object next();

}

/**
 * 集合接口
 *
 * @author zhenchao.wang 2019-11-19 17:25
 * @version 1.0.0
 */
public interface Aggregate {

    /**
     * 创建一个迭代器
     *
     * @return
     */
    Iterator iterator();

}
```

书架 BookShelf 和书籍 Book 的实现如下：

```java
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

/**
 * 书架
 *
 * @author zhenchao.wang 2019-11-19 17:25
 * @version 1.0.0
 */
public class BookShelf implements Aggregate {

    private Book[] books;
    private int count = 0;

    public BookShelf(int size) {
        this.books = new Book[size];
    }

    public Book getBook(int idx) {
        return books[idx];
    }

    public BookShelf addBook(Book book) {
        books[count++] = book;
        return this;
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(this);
    }

    private static class IteratorImpl implements Iterator {

        private BookShelf bookShelf;
        private int index = 0;

        IteratorImpl(BookShelf bookShelf) {
            this.bookShelf = bookShelf;
        }

        @Override
        public boolean hasNext() {
            return index < bookShelf.count;
        }

        @Override
        public Object next() {
            return bookShelf.getBook(index++);
        }
    }
}
```

测试如下：

```java
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
```
