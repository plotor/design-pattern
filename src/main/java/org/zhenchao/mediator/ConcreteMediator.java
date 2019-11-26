package org.zhenchao.mediator;

/**
 * @author zhenchao.wang 2019-11-26 14:10
 * @version 1.0.0
 */
public class ConcreteMediator implements Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    @Override
    public void changed(Colleague colleague) {
        // 某个参与对象状态发生变化，一般需要与其它参与对象进行交互
    }

    public ConcreteMediator setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
        return this;
    }

    public ConcreteMediator setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
        return this;
    }

}
