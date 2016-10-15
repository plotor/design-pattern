package org.zhenchao.factory.simple;

/**
 * 咖啡机
 *
 * @author zhenchao.wang 2016-10-15 14:52
 * @version 1.0.0
 */
public class CoffeeMaker {

    /**
     * 制作咖啡
     *
     * @param type
     */
    public void make(CoffeeType type) {

        Coffee coffee;
        if (CoffeeType.LATTE.equals(type)) {
            coffee = new Latte();
        } else if (CoffeeType.MOCHA.equals(type)) {
            coffee = new Mocha();
        } else if (CoffeeType.CAPPUCCINO.equals(type)) {
            coffee = new Cappuccino();
        } else {
            throw new IllegalArgumentException("Unknown coffee type!");
        }

        // 磨咖啡
        this.ground(coffee);

        // 煮咖啡
        this.boil(coffee);

        // 装杯
        this.pack(coffee);

    }

    /**
     * 制作咖啡
     *
     * @param type
     */
    public void make2(CoffeeType type) {

        Coffee coffee = CoffeeFactory.build(type);

        // 磨咖啡
        this.ground(coffee);

        // 煮咖啡
        this.boil(coffee);

        // 装杯
        this.pack(coffee);

    }

    /**
     * 磨咖啡
     */
    private void ground(Coffee coffee) {
        System.out.println(String.format("grounding %s", coffee.getName()));
    }

    /**
     * 煮咖啡
     */
    private void boil(Coffee coffee) {
        System.out.println(String.format("boiling %s", coffee.getName()));
    }

    /**
     * 装杯
     */
    private void pack(Coffee coffee) {
        System.out.println(String.format("packing %s", coffee.getName()));
    }
}
