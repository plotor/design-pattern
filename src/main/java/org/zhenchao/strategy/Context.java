package org.zhenchao.strategy;

/**
 * 环境角色
 *
 * @author zhenchao.wang 2017-04-16 22:00
 * @version 1.0.0
 */
public class Context {

    public static void main(String[] args) {

        Integer[] nums = new Integer[100];

        // 使用具体的策略类进行实例化
        SortStrategy<Integer> sortStrategy = new QuickSortStrategy<>();
        // SortStrategy<Integer> sortStrategy = new MergeSortStrategy<>();

        // 不管使用哪种具体策略，这里的调用方式不变，只是性能上有差异
        sortStrategy.sort(nums);

    }

}
