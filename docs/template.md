### 模板方法模式

模板方法（Template Method）模式的定义是在一个方法中定义一个算法的骨架，将具体逻辑延迟到子类中实现。组成模板的方法以抽象方法的形式被定义在父类中，父类只负责调度这些方法，而不管这些方法的具体实现。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象类 | AbstractClass | 抽象类 | 实现模板方法，并在其中声明被模板方法调用的抽象方法，这些抽象方法的实现交由子类完成
具体类 | ConcreteClass | 类 | 实现在 AbstractClass 中声明的抽象方法

#### 示例

这里用一个宋丹丹的经典小品中的一个笑话来举例。

> - 问：“请问，将大象装冰箱，拢共分几步？”
> - 答：“三步！第一步，将冰箱门打开；第二步，将大象放进去；第三步，将冰箱门关上。”

嘿嘿，将大象放冰箱，与我们平时将一串葡萄放入冰箱的步骤相同。但是这只是算法的流程相同，在具体实施时还是大有不同的。我们将整个执行流程称为模板方法，定义如下：

1. 开门（open）
2. 放入（putIn）
3. 关门（close）

这里的开门和关门都是相同的，所以我们可以只实现一次，但是放入的过程是视具体放入的东西而定的，需要延迟到子类中去实现。

抽象类和模板方法实现如下：

```java
public abstract class Packing {

    /**
     * 模板方法，将物品放入冰箱
     */
    public void putInTheRefrigerator() {
        this.open();
        this.putIn();
        this.close();
    }

    protected void open() {
        System.out.println("打开冰箱门");
    }

    /**
     * 放入物品
     */
    protected abstract void putIn();

    protected void close() {
        System.out.println("关闭冰箱门");
    }

}
```

- 将大象放入冰箱的实现类

```java
public class PackElephant extends Packing {

    @Override
    protected void putIn() {
        System.out.println("大象较大，先将其切成小块，再放入冰箱");
    }

}
```

- 将葡萄放入冰箱的实现类

```java
public class PackGrape extends Packing {

    @Override
    protected void putIn() {
        System.out.println("葡萄是水果，先用果篮装起来，再放入冰箱");
    }

}
```
