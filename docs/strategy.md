### 策略模式

策略（Strategy）模式是对一组算法的封装，对外界提供统一的接口，以屏蔽内部具体算法细节。

一个生动的例子就是 java 中的顺序表实现。对于一个 List 接口而言，一般会有如下两种常见的实例化方式供选择：

```java
List list = new ArrayList();
List list = new LinkedList();
```

这就是一个很好的策略模式的应用，ArrayList 和 LinkedList 都对外提供了相同的 API，只是在数据的内部存储上不一样，前者基于数组，后者基于链表。这就造成了二者各有自己的应用场景，如果是具有频繁的随机插入和删除的应用场景，推荐使用 LinkedList，其余场景一般都推荐使用 ArrayList。不过，这不是本文讨论的重点，本文的重点在于我们可以随便的在 List 对象构造的地方替换具体实例化的类，而不会影响已有代码中对于 List 对象的使用方式，区别仅在于代码的性能。

如果遇到以下情况，应该考虑使用策略模式：

1. 如果一个系统中存在许多具有相同 API，却仅仅只有行为上区别的类，应该考虑使用策略模式，让系统动态决定使用哪一种行为。
2. 如果一个对象具备多种行为，而这些行为的决策需要借助于多重选择语句，这种情况下可以将每一种行为转移到一个具体的策略类中。

__策略模式的优点：__

1. 提供了管理相关算法族的方法，策略类的等级结构定义了一个算法或行为族，恰当的使用继承可以将公共代码抽取到父类中实现，对外提供统一的 API。
2. 提供了可以替换继承关系的方法，支持了多组合少继承的思想。
3. 避免了多重转移语句选择逻辑，使得代码逻辑更清晰，易于维护。

__策略模式的缺点：__

1. 客户端必须知道所有的策略类，并且需要自己决策使用哪个类，因此客户端需要理解每一种策略类在算法或行为实现上的区别。
2. 如果策略较多，需要针对每一种策略提供具体的实现，必要的话可以采用享元模式避免创建大量细粒度对象。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
策略 | Strategy | 接口 / 抽象类 | 声明策略所必须的对外 API
具体策略 | ConcreteStrategy | 类 | 实现 Strategy，并提供对其中声明的 API 的具体实现
上下文 | Context | 类 | 使用 Strategy，实际使用哪个具体策略可以轻松替换

#### 示例

我们以排序算法为例，经典算法中提供了多种排序算法，每一种算法都有自己的适用场景。如果我们要设计一个排序算法基础包，很适合用策略模式来屏蔽各种算法的实现细节，而对外统一暴露一个 `sort(T[] elements)` 排序 API，具体使用哪种算法来排序，由上下文自己去决策。实现如下：

- 排序策略

```java
public interface SortStrategy<T extends Comparable<T>> {

    void sort(T[] elements);

}
```

- 具体排序策略

```java
public class QuickSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(T[] elements) {
        // 基于快速排序算法对 elements 执行排序
    }

}

public class MergeSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(T[] elements) {
        // 基于归并排序算法对 elements 执行排序
    }

}
```

- 上下文

```java
Integer[] nums = new Integer[100];

// 使用具体的策略类进行实例化
SortStrategy<Integer> sortStrategy = new QuickSortStrategy<>();
// SortStrategy<Integer> sortStrategy = new MergeSortStrategy<>();

// 不管使用哪种具体策略，这里的调用方式不变，只是性能上有差异
sortStrategy.sort(nums);
```

基于策略模式实现的算法基础包，上下文可以根据自己的需要来实例化具体的排序策略，必要的时候还可以自行扩展或动态替换，而具体的使用方式不需要改变。
