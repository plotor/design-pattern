package org.zhenchao.iterator;

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
