package org.zhenchao.flyweight.composite;

/**
 * 抽象结果处理器
 *
 * @author zhenchao.wang 2017-04-12 23:42
 * @version 1.0.0
 */
public abstract class AbstractResultHandler implements Comparable<AbstractResultHandler> {

    /** 基础优先级 */
    protected static final int BASE_PRIORITY = 0;

    /**
     * 处理函数
     *
     * @param obj
     */
    public abstract void handle(Object obj);

    /**
     * 返回处理器的优先级
     *
     * @return
     */
    public abstract int getPriority();

    @Override
    public int compareTo(AbstractResultHandler handler) {
        return handler.getPriority() - this.getPriority();
    }
}
