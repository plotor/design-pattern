package org.zhenchao.decorator.example;

import java.util.Date;

/**
 * 计算奖金的组件接口
 *
 * @author zhenchao.wang 2016-11-29 22:17
 * @version 1.0.0
 */
public abstract class Component {

    /**
     * 计算某人在某段时间的奖金
     *
     * @param user
     * @param begin
     * @param end
     * @return
     */
    public abstract double calcPrize(String user, Date begin, Date end);

}
