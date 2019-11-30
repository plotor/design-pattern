package org.zhenchao.flyweight.composite;

/**
 * 抽象结果处理器
 *
 * @author zhenchao.wang 2017-04-12 23:42
 * @version 1.0.0
 */
public abstract class ResultHandler implements Comparable<ResultHandler> {

    /** 内部状态：基础优先级 */
    protected static final int DEFAULT_PRIORITY = 0;

    /**
     * 处理函数
     *
     * @param obj
     */
    public abstract void handle(Object obj);

    public abstract int priority();

    @Override
    public int compareTo(ResultHandler that) {
        return that.priority() - this.priority();
    }

}
