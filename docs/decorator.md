### 装饰模式

当我们希望给一个类增加功能，或者让一个类更加具象化的时候，我们想到的会是继承。这一面向对象的基础特性曾经让我们在程序设计上向前迈进了一大步，但是在实际开发中，我们却又越来越多的听到要 “__少用继承，多用组合__”，目的是为了让代码更加的灵活，尽可能满足开闭原则。

如果一味的使用继承会给我们带来什么问题呢？在一些场景下会出现 __组合爆炸__。以咖啡为例，市场上的咖啡种类多到数不清，如果我们采用继承来实现每一种咖啡，那么需要实现的类的数目可想而知。不过，就如同咖啡的构成一样，许多类的功能都可以通过组合实现，也就是说我们可以在功能层面进行抽象，通过将原始对象与功能模块进行组合来生成符合要求的对象，这也就是装饰（Decorator）模式的思想，__动态地将功能附加到对象上__。

通过在装饰者（Decorator）中持有被装饰对象（Component）的实例，实现对被装饰对象进行增强。这种增强基于组合实现，是可插拔的，从而让代码的实现更加灵活，后续的扩展可以再增加一个装饰器或被装饰者，而无需修改原有的实现，所以说这类实现满足开闭原则。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
组件 | Component | 接口 / 抽象类 | 被装饰的组件
具体组件 | ConcreteComponent | 类 | 具体组件实现
装饰物 | Decorator | 抽象类 | 具备与 Component 相同的 API 定义，并持有 Component 类型对象，用于装饰该对象
具体装饰物 | ConcreteDecorator | 类 | 具体装饰物，继承自 Decorator

#### 示例

以咖啡为例，我们用装饰模式来实现一杯咖啡，设计思想就是将一杯咖啡分为咖啡（Component）和调味品（Decorator）两大类。针对各个具体的类型可以具体实现，最后通过组合来完成目标对象的构建。具体实现如下：

- 组件（咖啡）

```java
public abstract class Coffee {

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

- 具体组件

```java
public class WhiteCoffee extends Coffee {

    public WhiteCoffee() {
        super("white coffee");
    }

    @Override
    public double price() {
        return 10.0;
    }

}

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

- 装饰物（调味品）

```java
public abstract class CondimentDecorator extends Coffee {

    // 需要持有一个被装饰组件对象
    protected Coffee coffee;

    public CondimentDecorator(String name, Coffee coffee) {
        super(name);
        this.coffee = coffee;
    }

    @Override
    public abstract String getName();

}
```

- 具体装饰物

```java
public class MilkDecorator extends CondimentDecorator {

    private static final String NAME = "milk";

    public MilkDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 5.6D;
    }

    @Override
    public String getName() {
        return coffee.getName() + ", " + NAME;
    }

}

public class SugarDecorator extends CondimentDecorator {

    private static final String NAME = "sugar";

    public SugarDecorator(Coffee coffee) {
        super(NAME, coffee);
    }

    @Override
    public double price() {
        return coffee.price() + 1.8D;
    }

    @Override
    public String getName() {
        return coffee.getName() + ", " + NAME;
    }

}
```

- 客户端

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

我们可以自由的组合装饰者与被装饰者。
