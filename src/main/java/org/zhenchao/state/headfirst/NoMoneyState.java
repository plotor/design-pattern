package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:21
 * @version 1.0.0
 */
public class NoMoneyState extends State {

    public NoMoneyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        // 已投币，切换状态
        candyMachine.switchState(candyMachine.getHasMoneyState());
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，您还没有投币！");
    }

    @Override
    public void clickButton() {
        System.out.println("对不起，您还没有投币！");
    }

    @Override
    public void outputCandy() {
        System.out.println("对不起，您还没有投币！");
    }

}
