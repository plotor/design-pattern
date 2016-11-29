package org.zhenchao.decorator.example;

/**
 * @author zhenchao.wang 2016-11-29 22:55
 * @version 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        MonthPrizeDecorator mpd = new MonthPrizeDecorator(component);
        SumPrizeDecorator spd = new SumPrizeDecorator(mpd);
        System.out.println("zhangsan prize:\t" + spd.calcPrize("zhangsan", null, null));
        System.out.println("lisi prize:\t" + spd.calcPrize("lisi", null, null));

        GroupPrizeDecorator gpd = new GroupPrizeDecorator(spd);
        System.out.println("wanger prize:\t" + gpd.calcPrize("wanger", null, null));
    }

}
