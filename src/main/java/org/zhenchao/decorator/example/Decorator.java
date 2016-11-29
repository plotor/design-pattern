package org.zhenchao.decorator.example;

import java.util.Date;

/**
 * 装饰器
 *
 * @author zhenchao.wang 2016-11-29 22:24
 * @version 1.0.0
 */
public abstract class Decorator extends Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        return this.component.calcPrize(user, begin, end);
    }
}
