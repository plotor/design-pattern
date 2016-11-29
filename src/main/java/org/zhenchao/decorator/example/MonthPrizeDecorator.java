package org.zhenchao.decorator.example;

import java.util.Date;

/**
 * 装饰器对象，计算当月业务奖金
 *
 * @author zhenchao.wang 2016-11-29 22:39
 * @version 1.0.0
 */
public class MonthPrizeDecorator extends Decorator {

    public MonthPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double prize = super.calcPrize(user, begin, end);
        return prize + TestDB.saleroom.get(user) * 0.03;
    }
}
