package org.zhenchao.iterator;

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
