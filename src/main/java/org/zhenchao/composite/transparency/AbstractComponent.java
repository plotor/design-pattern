package org.zhenchao.composite.transparency;

/**
 * 抽象组件
 *
 * @author zhenchao.wang 2016-11-06 21:18
 * @version 1.0.0
 */
public interface AbstractComponent {

    /**
     * 打印
     *
     * @param parent
     */
    void print(String parent);

    /**
     * 添加组件
     *
     * @param component
     */
    default void addComponent(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除组件
     *
     * @param component
     */
    default void removeComponent(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取组件
     *
     * @param index
     * @return
     */
    default AbstractComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }
}
