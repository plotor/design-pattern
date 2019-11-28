package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 12:24
 * @version 1.0.0
 */
public class BaleCandyState extends State {

    public BaleCandyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("系统正在为您打包糖果，请稍后再投币！");
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，本次消费已生效，无法退币！");
    }

    @Override
    public void clickButton() {
        System.out.println("系统正在为您打包糖果，无需再按按钮！");
    }

    @Override
    public void outputCandy() {
        candyMachine.decreaseCount();
        System.out.println("糖果打包成功，请取走！");
        if (candyMachine.getCount() > 0) {
            // 还有剩余糖果
            candyMachine.switchState(candyMachine.getNoMoneyState());
        } else {
            // 糖果已卖完
            candyMachine.switchState(candyMachine.getNoCandyState());
        }
    }

}
