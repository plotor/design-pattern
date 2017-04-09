package com.javapatterns.strategy.sortarray;

public class Sorter {
    /**
     * @link aggregation
     * @directed
     */
    private SortStrategy sortStrategy;

    public void sort() {
        sortStrategy.sort();
    }

    public void setSortStrategy(SortStrategy sort) {
        this.sortStrategy = sort;
    }
}
