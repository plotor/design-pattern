package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:23
 * @version 1.0.0
 */
public class HasMoneyState extends State {

    public HasMoneyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("您已投入钱币，无需再继续投入！");
    }

    @Override
    public void refundCoins() {
        System.out.println("系统正在退款，请稍等...");
        // 用户要求退款，退款完成后切换状态
        candyMachine.switchState(candyMachine.getNoMoneyState());
    }

    @Override
    public void clickButton() {
        System.out.println("正在为您准备糖果...");
        // 用户已投币，要求出货
        candyMachine.switchState(candyMachine.getBaleCandyState());
    }

    @Override
    public void outputCandy() {
        System.out.println("请先按下按钮！");
    }

}
