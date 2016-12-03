&emsp;&emsp;当我们希望给一个类增加功能，或者让一个类更加具象化的时候，我们想到的会是继承，这一面向对象的基础特性曾经让我们在程序上向前迈进了一大步，但是在实际开发中，我们却又越来越多的听到要 __“少用继承，多用组合”__，目的是为了让代码更加的灵活，尽可能满足 __“开-闭”__ 原则。

&emsp;&emsp;如果用一味的使用继承会给我们带来什么问题呢，在一些场景下会出现 __组合爆炸__，以咖啡为例，市场上的咖啡种类多到数不清，如果我们采用继承来实现每一种咖啡，那么需要实现的类的数目可想而知，但是就如同咖啡的构成一样，许多类的功能都是通过组合而成，也就是说我们可以在功能层面进行抽象，通过将原始对象与功能模块进行组合来生成符合要要求的对象，这也就是装饰模式的思想： __动态地将责任附加到对象上__。

### 装饰模式的实现
&emsp;&emsp;以咖啡为例，我们用装饰模式来实现一杯咖啡，设计思想就是将一杯咖啡分为 __咖啡和调味品__ 两大类。针对各个具体的类型可以具体，最后通过组合来完成目标对象的构建。UML图如下：

![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/decorator.png?raw=true)

&emsp;&emsp;通过在装饰者中持有被装饰对象的实例，从而可以对被装饰对象进行增强，而这种增强基于组合实现，是可插拔的，从而让代码的实现更加灵活，后续的扩展可以再增加一个装饰器或被装饰者，而无需修改原有的实现，所以说这类实现满足“开-闭”原则。具体实现如下：

- __咖啡抽象类__

```java
/**
 * 咖啡抽象
 *
 * @author zhenchao.wang 2016-12-03 12:13
 * @version 1.0.0
 */
public abstract class Coffee {

    /** 咖啡名称 */
    private String name;

    public Coffee(String name) {
        this.name = name;
    }

    /**
     * 价格
     *
     * @return
     */
    public abstract double price();

    public String getName() {
        return name;
    }
}
```

- 具体的咖啡类：白咖啡、黑咖啡

```java
/**
 * 白咖啡
 *
 * @author zhenchao.wang 2016-12-03 12:17
 * @version 1.0.0
 */
public class WhiteCoffee extends Coffee {

    public WhiteCoffee() {
        super("white coffee");
    }

    @Override
    public double price() {
        return 10.0;
    }

}

/**
 * 黑咖啡
 *
 * @author zhenchao.wang 2016-12-03 12:23
 * @version 1.0.0
 */
public class BlackCoffee extends Coffee {

    public BlackCoffee() {
        super("black coffee");
    }

    @Override
    public double price() {
        return 8.0;
    }
}
```

- 调味品装饰类

```java
/**
 * 调料装饰器
 *
 * @author zhenchao.wang 2016-12-03 12:29
 * @version 1.0.0
 */
public abstract class CondimentDecorator extends Coffee {

    protected static final String SEP = ", ";

    protected Coffee coffee;

    public CondimentDecorator(String name, Coffee coffee) {
        super(name);
        this.coffee = coffee;
    }

    /**
     * 标签信息
     *
     * @return
     */
    public abstract String getName();
}
```

- 具体调味品：牛奶、焦糖

```java
/**
 * 调味品：牛奶
 *
 * @author zhenchao.wang 2016-12-03 12:34
 * @version 1.0.0
 */
public class MilkDecorator extends CondimentDecorator {

    private static final String NAME = "milk";

    public MilkDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 5.6;
    }

    @Override
    public String getName() {
        return StringUtils.join(coffee.getName(), SEP, NAME);
    }
}

/**
 * 调味品：糖
 *
 * @author zhenchao.wang 2016-12-03 12:45
 * @version 1.0.0
 */
public class SugarDecorator extends CondimentDecorator {

    private static final String NAME = "sugar";

    public SugarDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 1.8;
    }

    @Override
    public String getName() {
        return StringUtils.join(coffee.getName(), SEP, NAME);
    }
}
```

- 测试

我们可以自由的组合装饰者与被装饰者，以构建目标对象：

```java
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
```

输出如下：

```text
white coffee, milk
15.6
white coffee, sugar, milk
17.4
black coffee, sugar
9.8
```