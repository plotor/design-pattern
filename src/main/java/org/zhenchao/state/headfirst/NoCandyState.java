package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:13
 * @version 1.0.0
 */
public class NoCandyState extends State {

    public NoCandyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("对不起，糖果已卖完！");
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，您还没有投入钱币！");
    }

    @Override
    public void clickButton() {
        System.out.println("对不起，糖果已卖完！");
    }

    @Override
    public void outputCandy() {
        System.out.println("对不起，糖果已卖完！");
    }

}
