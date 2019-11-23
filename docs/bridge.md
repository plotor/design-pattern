### 桥接模式

- [类的层次结构](#类的层次结构)
- [角色](#角色)
- [示例](#示例)
    - [抽象部分](#抽象部分)
    - [实现部分](#实现部分)
    - [桥接](#桥接)

---

桥接（Bridge）模式用于将抽象部分（Abstraction）与其实现部分（Implementor）相分离，使二者都可以独立变化，并以桥接的方式将二者联系起来，建立起彼此之间的桥梁。但是需要注意的一点是，这个桥接是单向的，只能是抽象部分去使用具体实现部分的对象，不能反过来。其用意是让抽象部分和实现部分可以相互独立的变化和扩充，其中抽象部分基于实现部分对外提供 API 服务。

#### 类的层次结构

我们在引入继承时，主要目的分为两大类：__扩展功能__ 和 __扩展实现__。

假设我们现在有一个类 A，如果 A 中定义的方法不能满足我们的需求，那么我们一般会想到继承 A 派生出一个新的类 B，并在类 B 中增加新的功能。此时类 B 相对于类 A 是在功能上进行扩充，我们称这种层次结构为 __类的功能层次结构__。

还有另外一种层次结构叫 __类的实现层次结构__。假设我们现在定义好了一个抽象类 Animal，并在其中声明了各种动物应该具备的功能（抽象方法），那么我们可以继承 Animal 抽象类实现一个 Person 类表示人类，继承 Animal 类实现一个 Dog 类表示小狗。

所以当我们要编写子类时一般需要思考，我们是要增加功能还是要增加实现。当继承关系比较简单的时候我们一般会将二者混在一起，但是当继承关系变得复杂时，需要考虑将类的功能层次和类的实现层次分离开来。桥接模式的意图就在于建立分离后的类的功能层次和类的实现层次之间的关系。在桥接模式中我们将类的功能层次称为抽象部分（Abstraction），而将类的实现层次称为实现部分（Implementor）。

桥接相对于继承而言，一个显著的特点就是继承的变化是一维的，而桥接的变化是二维的。以下文将要介绍的消息发送示例而言，消息的类型有 3 种，发送消息的方式也有 3 种，如果要实现全的话，基于继承的方式需要实现 `3 × 3 = 9` 个类，而基于桥接的方式只需要实现 `3 + 3 = 6` 个类。当消息类型和发送方式逐渐增多时，二者之间的差距会越明显，桥接模式引入组合机制，大大减少了继承所派生出的类的数目。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象部分 | Abstraction | 抽象类 | 一般维护一个 Implementor 的对象引用，并调用该对象的 API 完成 Abstraction 中定义的方法逻辑
扩展抽象部分 | RefinedAbstraction | 类 | 扩展抽象部分，一般在该类中定义跟实际业务相关的方法，这些方法的实现一般依赖 Abstraction 中定义的方法，也可能需要调用 Implementor 中的方法予以完成
实现部分 | Implementor | 接口 | 定义供 Abstraction 调用的 API，也就是说 Abstraction 定义了基于这些基本操作的较高层次的 API。如果实现部分能够明确只有一种实现，那么 Implementor 可以直接实现成类，而无需定义成接口，也就是将实现部分和具体实现部分合二为一
具体实现部分 | ConcreteImplementor | 类 | 实现自 Implementor 接口

- 实现部分

```java
public interface Implementor {
    void operationImpl();
}

public class ConcreteImplementor1 implements Implementor {
    @Override
    public void operationImpl() {
        // 具体实现
    }
}

public class ConcreteImplementor2 implements Implementor {
    @Override
    public void operationImpl() {
        // 具体实现
    }
}
```

- 抽象部分

```java
public abstract class Abstraction {

    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation() {
        implementor.operationImpl();
    }
}

public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void otherOperation() {
        // 其它操作
    }
}
```

__谁来负责桥接？__

即谁来负责建立抽象部分和实现部分的关系，也就是说谁来负责创建 Implementor 对象，并将其设置到抽象部分的对象中去。一般有以下几种方式：

1. 由客户端负责创建 Implementor 对象，并在创建抽象部分对象时将其设置到抽象部分对象中；
2. 由抽象部分在自己的构造方法中自行创建对应的 Implementor 对象，或是基于接收的参数选择创建相应的 Implementor 对象；
3. 在 Abstraction 中创建一个默认的 Implementor 对象，并允许子类修改；
4. 使用工厂模式创建具体的 Implementor 对象，抽象部分通过工厂获取 Implementor 对象；
5. 使用 IoC 容器管理 Implementor 对象，并注入到 Abstraction 中。

#### 示例

假设我们现在要提供一个发送信息的功能，可以发送普通信息（CommonMessage）、加急信息（UrgencyMessage）和特急信息（SpecialUrgencyMessage）。发送信息的方式可以是站内短消息（SmsMessage），也可以是电子邮件（EmailMessage）。

如果一味的使用继承，那么随着消息类型和发送方式的增加，继承关系的复杂度将会以指数的方式增加。这个时候我们就需要将类的功能层次结构和类的实现层次结构分离开来，对于本例而言就是将消息的类型和发送方式分离开来，独立实现和扩充，并以桥接的方式建立联系。实现如下：

##### 抽象部分

- 抽象信息

```java
public abstract class AbstractMessage {

    private MessageImplementor implementor;

    public AbstractMessage(MessageImplementor implementor) {
        this.implementor = implementor;
    }

    /**
     * 发送信息
     *
     * @param message
     * @param to
     */
    public void sendMessage(String message, String to) {
        implementor.send(message, to);
    }
}
```

- 普通消息

```java
public class CommonMessage extends AbstractMessage {

    public CommonMessage(MessageImplementor implementor) {
        super(implementor);
    }
}
```

- 加急消息

```java
public class UrgencyMessage extends AbstractMessage {

    public UrgencyMessage(MessageImplementor implementor) {
        super(implementor);
    }

    @Override
    public void sendMessage(String message, String to) {
        String urgencyMessage = "加急：" + message;
        // 发送加急信息
        super.sendMessage(urgencyMessage, to);
    }

    public void watch(String messageId) {
        // 执行监控逻辑
    }

}
```

- 特急消息

```java
public class SpecialUrgencyMessage extends AbstractMessage {

    public SpecialUrgencyMessage(MessageImplementor implementor) {
        super(implementor);
    }

    @Override
    public void sendMessage(String message, String to) {
        String urgencyMessage = "特急：" + message;
        // 发送特急信息
        super.sendMessage(urgencyMessage, to);
    }

    public void hurry(String messageId) {
        // 执行催促逻辑
    }
}
```

##### 实现部分

- 消息发送方式抽象

```java
public interface MessageImplementor {

    /**
     * 发送信息
     *
     * @param message
     * @param to
     */
    void send(String message, String to);

}
```

- 站内短消息

```java
public class SmsMessage implements MessageImplementor {

    @Override
    public void send(String message, String to) {
        System.out.println("[站内短消息] 发送信息 [" + message + "] 给 [" + to + "]");
    }

}
```

- 电子邮件

```java
public class EmailMessage implements MessageImplementor {

    @Override
    public void send(String message, String to) {
        System.out.println("[电子邮件] 发送信息 [" + message + "] 给 [" + to + "]");
    }

}
```

##### 桥接

这里我们以简单的由客户端负责创建 Implementor 对象，并在创建抽象部分对象时将其设置到抽象部分对象中的方式建立桥接，实现如下：

```java
// 以站内短消息的形式发送
MessageImplementor implementor = new SmsMessage();

// 创建一条普通信息对象
AbstractMessage message = new CommonMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");

// 创建一条加急消息
message = new UrgencyMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");

// 创建一条特急消息
message = new SpecialUrgencyMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");

// 替换实现方式为电子邮件
implementor = new EmailMessage();

// 创建一条普通信息对象
message = new CommonMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");

// 创建一条加急消息
message = new UrgencyMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");

// 创建一条特急消息
message = new SpecialUrgencyMessage(implementor);
message.sendMessage("请喝一杯茶", "小李");
```
