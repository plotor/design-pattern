package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:14
 * @version 1.0.0
 */
public class CandyMachine {

    private NoCandyState noCandyState;
    private BaleCandyState baleCandyState;
    private NoMoneyState noMoneyState;
    private HasMoneyState hasMoneyState;

    private int count;
    private State state;

    public CandyMachine(int count) {
        this.count = count;
        this.noCandyState = new NoCandyState(this);
        this.baleCandyState = new BaleCandyState(this);
        this.noMoneyState = new NoMoneyState(this);
        this.hasMoneyState = new HasMoneyState(this);
        // 设置初始状态
        this.state = this.count > 0 ? this.noMoneyState : this.noCandyState;
    }

    public void putInCoins() {
        state.putInCoins();
    }

    public void refundCoins() {
        state.refundCoins();
    }

    public void clickButton() {
        state.clickButton();
    }

    public void outputCandy() {
        state.outputCandy();
    }

    public void decreaseCount() {
        this.count--;
    }

    public CandyMachine switchState(State state) {
        this.state = state;
        return this;
    }

    public NoCandyState getNoCandyState() {
        return noCandyState;
    }

    public BaleCandyState getBaleCandyState() {
        return baleCandyState;
    }

    public NoMoneyState getNoMoneyState() {
        return noMoneyState;
    }

    public HasMoneyState getHasMoneyState() {
        return hasMoneyState;
    }

    public int getCount() {
        return count;
    }
}
