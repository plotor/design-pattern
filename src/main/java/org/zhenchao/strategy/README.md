## 策略模式

策略模式是对一组算法的封装，对外界提供统一的接口，并屏蔽内部具体算法细节。

一个生动的例子就是 java 中的顺序表实现，对于一个 `List` 接口，一般会有如下两种常见的实例化方式供选择：

```java
List list = new ArrayList();
List list = new LinkedList();
```

这就是一个很好的策略模式的应用，`ArrayList` 和 `LinkedList` 都对外提供了相同的 API，只是在数据的内部存储上不一样，前者基于数组，后者基于链表，这就造成了二者各有自己的应用场景，如果是具有频繁的随机插入和删除的应用场景，推荐使用后者，其余情况一般都推荐前者，但这不是本文讨论的重点，本文的重点在于我们可以随便的在 `list` 对象构造的地方替换具体实例化的类，而不会去影响代码中对于 `list` 对象的使用方式，区别仅仅在于代码的性能。

### 角色定义

策略模式涉及三个角色：

- 抽象策略角色

一个接口或抽象类，定义了所有对外的API接口。

- 具体策略角色

实现了抽象策略角色定义接口的具体的类，封装了具体算法和行为。

- 环境角色

持有一个策略类的引用。

### 举例说明

我们以排序算法为例，经典算法中提供了多种排序算法，每一种算法都有自己的适用场景，如果我们要设计一个排序算法基础包，很适合用策略模式来屏蔽各种算法的实现细节，而只是对外暴露一个统一的 `sort(T[] item)` 排序算法，具体使用哪种算法来排序，由客户端自己去决策。

实现如下：

- 抽象排序算法策略类

```java
/**
 * 排序算法抽象
 *
 * @author zhenchao.wang 2017-04-16 21:55
 * @version 1.0.0
 */
public interface AbstractSortAlgorithm<T extends Comparable<T>> {

    /**
     * 排序
     *
     * @param item
     */
    void sort(T[] item);

}
```

- 具体排序算法策略类

```java
/**
 * 快速排序算法
 *
 * @author zhenchao.wang 2017-04-16 21:58
 * @version 1.0.0
 */
public class QuickSort<T extends Comparable<T>> implements AbstractSortAlgorithm<T> {

    @Override
    public void sort(T[] item) {

    }

}

/**
 * 归并排序
 *
 * @author zhenchao.wang 2017-04-16 21:59
 * @version 1.0.0
 */
public class MergeSort<T extends Comparable<T>> implements AbstractSortAlgorithm<T> {

    @Override
    public void sort(T[] item) {

    }

}
```

- 环境角色

```java
/**
 * 环境角色
 *
 * @author zhenchao.wang 2017-04-16 22:00
 * @version 1.0.0
 */
public class Context {

    public static void main(String[] args) {

        Integer[] item = new Integer[100];

        // 用具体的策略类进行实例化
        AbstractSortAlgorithm<Integer> sortAlgorithm = new QuickSort<>();
        // AbstractSortAlgorithm<Integer> sortAlgorithm = new MergeSort<>();

        // 不管sortAlgorithm怎么实例化，这里的调用方式不变，只是性能上有差异
        sortAlgorithm.sort(item);

    }

}
```

基于策略模式的算法基础包实现，客户端可以根据自己的需要来实例化策略对象，必要的时候还可以自己扩展，而具体的使用方式不需要改变。

### 应用场景

如果遇到下面的情况，应该考虑使用策略模式：

1. 如果一个系统中存在很多具有相同接口，却仅仅只有行为上区别的类，应该考虑使用策略模式，让系统动态决定使用哪一种行为

2. 如果一个对象具备多种行为，而这些行为的决策需要借助于多重选择语句，这种情况下可以将每一种行为转移到一个具体的策略类中。

### 模式优缺点

__策略模式的优点：__

1. 提供了管理相关算法族的方法，策略类的等级结构定义了一个算法或行为族，恰当的使用继承可以将公共代码移到父类里面，对外提供统一的API。

2. 提供了可以替换继承关系的方法，支持了多组合少继承的思想。

3. 避免了多重转移语句选择逻辑，使得代码逻辑更清晰，易于维护。

__策略模式的缺点：__

1. 客户端必须知道所有的策略类，并且需要自己决策使用哪个类，因此客户端需要理解每一种策略类在算法或行为实现上的区别。

2. 如果策略较多，会造成很多的策略类，必要的话可以采用享元模式来减少细粒度对象。