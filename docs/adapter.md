### 适配器模式

适配器（Adapter）模式，也称为包装器（Wrapper）模式，是我们日常生活中的比较常见的一个物件，比如 Android 和 iOS 充电线的转接头、HDMI 到 VGA 的转接器等等，其作用是在不兼容的场景下提供一种兼容的解决方案。

在程序设计中，我们将逻辑抽象成为一个个模块，通过对外暴露接口来提供服务，当需求方调用我们的接口的时候也会存在不兼容的情况，这个时候我们就可以通过适配器模式，在不改变原有代码的前提下，去解决兼容性问题。

在适配器模式的实现中包含 __对象适配器__ 和 __类适配器__ 两种，前者利用组合将被适配的类连接到适配器中进行兼容性转换，而后者则是基于继承（更准确的说是多重继承）访问被适配的类和目标类的 API 进行兼容性转换。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
对象 | Target | 类 / 接口 | 定义所需要的方法，暴露给请求者
请求者 | Client | 类 | 调用 Target 类中的方法完成具体的逻辑
被适配者 | Adaptee | 类 | 被适配的类，一般是因为其中定义的方法不能直接满足需求
适配器 | Adapter | 类 | 适配 Adaptee 中的方法以满足 Target 的需求，可以通过继承或委托的方式实现

#### 示例

本示例中我们定义 Pinter 接口作为 Target 类，Banner 类作为被适配者，Printer 接口和 Banner 类定义如下：

```java
public interface Printer {

    /**
     * 弱化字符串显示
     */
    void printWeak();

    /**
     * 强化字符串显示
     */
    void printStrong();

}

public class Banner {

    private String text;

    public Banner(String text) {
        this.text = text;
    }

    public void showWithParen() {
        System.out.println("(" + text + ")");
    }

    public void showWithAster() {
        System.out.println("*" + text + "*");
    }
}
```

当 Client 请求 `Printer#printWeak` 方法时，我们期望真正调用的是 `Banner#showWithParen` 方法，而请求 `Printer#printStrong` 方法时真正调用的是 `Banner#showWithAster` 方法。下面分别基于类适配器模式和对象适配器模式进行实现。

##### 类适配器模式

类适配器模式基于继承，准确的说应该是多继承，不过 java 对于多重继承不提供支持，所以这一实现方式相对于对象适配器在 java 中使用范围要少很多。针对一些场景还是可以采用这种方式，类适配器模式中的适配器（Adapter）、被适配者（Adaptee），以及目标接口（Target）的关系如下图：

![image](https://www.zhenchao.org/images/2016/adapter-2.png?raw=false)

适配器实现了目标接口，并继承被适配类，并在适配器中实现兼容逻辑。具体实现如下：

```java
public class PrintAdapter extends Banner implements Printer {

    public PrintAdapter(String text) {
        super(text);
    }

    @Override
    public void printWeak() {
        super.showWithParen();
    }

    @Override
    public void printStrong() {
        super.showWithAster();
    }
}
```

##### 对象适配器模式

对象适配器通过组合的方式将被适配的类连接到适配器中，然后适配器负责适配的逻辑，适配器（Adapter）、被适配者（Adaptee），以及目标接口（Target）的关系如下图：

![image](https://www.zhenchao.org/images/2016/adapter-1.png?raw=false)

适配器通过持有被适配类的对象，并将其包装成目标接口，供需求方调用，从而可以在不修改原有代码的基础上达到兼容的目的。

仍然以前面的示例，基于对象适配器模式的实现如下：

```java
public class PrintDelegator implements Printer {

    private Banner banner;

    public PrintDelegator(Banner banner) {
        this.banner = banner;
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
```

上面介绍的适配器都是单向适配器，即将被适配的类适配满足目标接口，这里面 Target 和 Adaptee 角色是确定的，实际上这两个角色是可以互换的，而这两个角色的互换可以通过双向适配器来完成，从而实现双向的兼容。

为什么不直接调用 Target 类中的方法，而是需要这样有些多此一举的周折一番呢？主要是因为在实际应用开发中，我们一般并非是从零开始编码，一些时候我们需要调用一些既定存在的 Target 类，但是可能存在 API 不兼容的情况。此时，一种方式就是直接修改 Target 类，但是这些类已经经历过实际运行的检验，修改操作可能会引入新的 bug，此时引入适配器模式可以减少引入新 bug 的可能性。
