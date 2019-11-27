package org.zhenchao.memento.graphic;

import org.apache.commons.lang3.RandomUtils;
import org.zhenchao.memento.Memento;

/**
 * @author zhenchao.wang 2019-11-27 14:14
 * @version 1.0.0
 */
public class Gamer {

    private static class MementoImpl implements Memento {

        private int money;

        MementoImpl(int money) {
            this.money = money;
        }

        int getMoney() {
            return money;
        }
    }

    /** 持有的金钱数目 */
    private int money;

    public Gamer(int money) {
        this.money = money;
    }

    public boolean play() {
        // 掷骰子
        int dice = RandomUtils.nextInt(1, 7);
        if (dice % 2 == 1) {
            money += 100;
            System.out.println("金钱增加 100，额度： " + money);
            return true;
        } else if (dice == 2 || dice == 4) {
            money /= 2;
            System.out.println("金钱减半，额度： " + money);
        } else {
            System.out.println("什么都没有发生，额度： " + money);
        }
        return false;
    }

    public Memento createMemento() {
        // 创建备忘录对象
        return new MementoImpl(money);
    }

    public void restoreMemento(Memento memento) {
        // 从备忘录中恢复状态
        MementoImpl mementoImpl = (MementoImpl) memento;
        this.money = mementoImpl.getMoney();
    }

    public int getMoney() {
        return money;
    }

}
