package org.zhenchao.decorator.example;

import java.util.Date;

/**
 * 装饰器对象，计算累计奖金
 *
 * @author zhenchao.wang 2016-11-29 22:42
 * @version 1.0.0
 */
public class SumPrizeDecorator extends Decorator {

    public SumPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        double prize = super.calcPrize(user, begin, end);
        return prize + 1000000 * 0.001;
    }
}
