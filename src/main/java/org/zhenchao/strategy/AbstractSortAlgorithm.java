package org.zhenchao.strategy;

/**
 * 排序算法抽象
 *
 * @author zhenchao.wang 2017-04-16 21:55
 * @version 1.0.0
 */
public interface AbstractSortAlgorithm<T extends Comparable<T>> {

    /**
     * 排序
     *
     * @param item
     */
    void sort(T[] item);

}
