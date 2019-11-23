package org.zhenchao.strategy;

/**
 * 排序算法抽象
 *
 * @author zhenchao.wang 2017-04-16 21:55
 * @version 1.0.0
 */
public interface SortStrategy<T extends Comparable<T>> {

    void sort(T[] elements);

}
