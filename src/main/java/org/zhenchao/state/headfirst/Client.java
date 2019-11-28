package org.zhenchao.state.headfirst;

/**
 * @author zhenchao.wang 2019-11-28 14:09
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        CandyMachine candyMachine = new CandyMachine(3);
        candyMachine.putInCoins();
        candyMachine.clickButton();
        candyMachine.outputCandy();
    }

}
