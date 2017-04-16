package org.zhenchao.strategy;

/**
 * 环境角色
 *
 * @author zhenchao.wang 2017-04-16 22:00
 * @version 1.0.0
 */
public class Context {

    public static void main(String[] args) {

        Integer[] item = new Integer[100];

        // 用具体的策略类进行实例化
        AbstractSortAlgorithm<Integer> sortAlgorithm = new QuickSort<>();
        // AbstractSortAlgorithm<Integer> sortAlgorithm = new MergeSort<>();

        // 不管sortAlgorithm怎么实例化，这里的调用方式不变，只是性能上有差异
        sortAlgorithm.sort(item);

    }

}
