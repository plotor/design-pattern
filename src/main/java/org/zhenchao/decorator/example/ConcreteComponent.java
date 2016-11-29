package org.zhenchao.decorator.example;

import java.util.Date;

/**
 * @author zhenchao.wang 2016-11-29 22:23
 * @version 1.0.0
 */
public class ConcreteComponent extends Component {

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        return 0;
    }

}
