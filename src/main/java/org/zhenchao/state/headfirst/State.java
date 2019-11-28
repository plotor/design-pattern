package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:05
 * @version 1.0.0
 */
public abstract class State {

    // 糖果机对象
    protected CandyMachine candyMachine;

    public State(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    // 投币
    public abstract void putInCoins();

    // 退币
    public abstract void refundCoins();

    // 按下出货按钮
    public abstract void clickButton();

    // 出货
    public abstract void outputCandy();

}
