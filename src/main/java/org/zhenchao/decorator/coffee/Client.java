package org.zhenchao.decorator.coffee;

/**
 * @author zhenchao.wang 2016-12-03 12:50
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Coffee whiteCoffee = new WhiteCoffee();
        Coffee blackCoffee = new BlackCoffee();

        // 牛奶白咖啡（不加糖）
        Coffee whiteMilkCoffee = new MilkDecorator(whiteCoffee);
        System.out.println(whiteMilkCoffee.getName());
        System.out.println(whiteMilkCoffee.price());

        // 牛奶白咖啡（加糖）
        Coffee whiteSugarMilkCoffee = new MilkDecorator(new SugarDecorator(whiteCoffee));
        System.out.println(whiteSugarMilkCoffee.getName());
        System.out.println(whiteSugarMilkCoffee.price());

        // 黑焦糖咖啡
        Coffee blackSugarCoffee = new SugarDecorator(blackCoffee);
        System.out.println(blackSugarCoffee.getName());
        System.out.println(blackSugarCoffee.price());

    }

}
