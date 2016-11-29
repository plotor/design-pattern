package org.zhenchao.decorator.example;

import java.util.Date;
import java.util.Map;

/**
 * 装饰器对象，计算当月团队业务奖金
 *
 * @author zhenchao.wang 2016-11-29 22:44
 * @version 1.0.0
 */
public class GroupPrizeDecorator extends Decorator {

    public GroupPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double prize = super.calcPrize(user, begin, end);
        return prize + TestDB.saleroom.entrySet().stream().map(Map.Entry::getValue).reduce(Double::sum).orElse(0.0) * 0.01;
    }
}
