### 观察者模式

- [角色](#角色)
    - [各角色实现](#各角色实现)
    - [Java 内建的观察者模式](#java-内建的观察者模式)
    - [推模型和拉模型](#推模型和拉模型)
- [示例](#示例)

---

观察者（Observer）模式用于定义目标对象（Subject）间的一种一对多的依赖关系，当一个对象的状态发生变化时，所有订阅的观察者都会收到通知，并被自动更新。观察者和目标对象之间是单向依赖的，只有观察者依赖于目标对象，而目标对象不会依赖于观察者。彼此之间联系的主动权掌握在目标对象手中，只有目标对象知道什么时候需要通知观察者，而观察者始终是被动的。目标对象在看待观察者时可以一视同仁，当然也可以依据具体的业务场景区别对待。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
目标对象 | Subject | 抽象类 / 接口 | 一个目标对象可以被多个观察者所观察，目标对象维护对观察者的注册和注销，当目标对象状态发生变化时，目标对象需要通知所有在册且有效的观察者
具体目标对象 | ConcreteSubject | 类 | 具体目标对象实现
观察者 | Observer | 抽象类 / 接口 | 定义观察者，提供被目标对象通知时对应的更新方法（用于相应的业务处理），可以在该方法中回调目标对象，以获取目标对象的的数据
具体观察者 | ConcreteObserver | 类 | 具体观察者实现，可以有多个

##### 各角色实现

- 目标对象

```java
public abstract class Subject {

    /**
     * 注册观察者
     *
     * @param observer
     */
    public abstract void register(Observer observer);

    /**
     * 注销观察者
     *
     * @param observer
     */
    public abstract void unregister(Observer observer);

    /**
     * 通知在册的所有观察者
     */
    protected abstract void notifyObservers();

}

public class ConcreteSubject extends Subject {

    private List<Observer> observers = new LinkedList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    protected void notifyObservers() {
        for (final Observer observer : observers) {
            observer.update(this);
        }
    }

}
```

- 观察者

```java
public interface Observer {

    /**
     * 收到通知后触发更新操作
     *
     * @param subject 观察的目标对象
     */
    void update(Subject subject);

}

public class ConcreteObserver implements Observer {

    @Override
    public void update(Subject subject) {
        // 可以通过 subject 对象获取目标对象的状态信息
        System.out.println(this.getClass() + " is notified.");
    }

}
```

##### Java 内建的观察者模式

Java 为观察者模式提供了原生支持，JDK 内置抽象观察者类 `java.util.Observer` 和抽象主题类 `java.util.Observable`。基于 java 内建的观察者模式修改上述示例的实现如下：

- 具体目标对象

```java
public class ConcreteSubject extends Observable {

    private String data = "";

    public String getData() {
        return data;
    }

    public ConcreteSubject setData(String data) {
        this.data = data;
        this.notifyObservers();
        return this;
    }

}
```

- 具体观察者

```java
public class ConcreteObserver implements Observer {

    @Override
    public void update(Observable subject, Object arg) {
        ConcreteSubject cs = (ConcreteSubject) subject;
        System.out.println(this.getClass() + ", data changed: " + cs.getData());
    }

}
```

##### 推模型和拉模型

在观察者模式的实现中，又分为推模型和拉模型。

__推模型__ 是指目标对象主动向观察者推送目标对象的详细信息，不论观察者是否需要，推送的信息一般是目标对象的全部或部分数据，相当于广播通信。

__拉模型__ 是指目标对象在通知观察者的时候，只传递少量信息，如果观察者需要更具体的信息，则由观察者主动请求目标对象获取，相当于观察者是从目标对象拉数据。具体实现就是在目标对象通知观察者时会将自身 this 引用传递给观察者，观察者如果需要更多的数据可以基于该引用获取。

上述示例在实现上采用了拉模型。

#### 示例

下面以订阅报纸的场景为例，读者可以订阅报纸，当报纸有更新时会通知所有订阅的读者。这里的报纸就是 Subject，而读者就是 Observer。其中 Observer 和 Subject 类的实现可以直接复用上面的示例，但是考虑 Subject 中注册、注销，以及通知的逻辑一般都是通用的，所以我们将 Subject 修改为如下：

```java
public abstract class Subject {

    private List<Observer> observers = new LinkedList<>();

    /**
     * 注册观察者
     *
     * @param observer
     */
    public void register(Observer observer) {
        observers.add(observer);
    }

    /**
     * 注销观察者
     *
     * @param observer
     */
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知在册的所有观察者
     */
    protected void notifyObservers() {
        for (final Observer observer : observers) {
            observer.update(this);
        }
    }

}
```

- 具体主题：报纸

```java
public class NewsPaper extends Subject {

    private String content;

    public String getContent() {
        return content;
    }

    public NewsPaper setContent(String content) {
        this.content = content;
        // 通知所有的观察者，数据有更新了
        this.notifyObservers();
        return this;
    }

}
```

- 具体观察者：读者

```java
public class Reader implements Observer {

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        NewsPaper np = (NewsPaper) subject;
        System.out.println(name + " 收到报纸更新的通知了，报纸摘要内容： " + np.getContent());
    }

}
```

- 客户端

```java
// 创建一个报纸目标对象
NewsPaper newsPaper = new NewsPaper();

// 创建多个读者
Reader reader1 = new Reader("张三");
Reader reader2 = new Reader("李四");
Reader reader3 = new Reader("王二");

// 订阅报纸
newsPaper.register(reader1);
newsPaper.register(reader2);
newsPaper.register(reader3);

// 报纸内容更新，会通知所有订阅的读者
newsPaper.setContent("观察者模式");
```
