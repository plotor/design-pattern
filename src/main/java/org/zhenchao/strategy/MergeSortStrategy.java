package org.zhenchao.strategy;

/**
 * 归并排序
 *
 * @author zhenchao.wang 2017-04-16 21:59
 * @version 1.0.0
 */
public class MergeSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(T[] elements) {
        // 基于归并排序算法对 elements 执行排序
    }

}
