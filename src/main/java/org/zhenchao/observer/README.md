## 观察者模式

> 观察者模式的别名有多种，如下：
>
> 1. 发布-订阅模式（Publish/Subscribe）
> 2. 模型-视图模式（Model/View）
> 3. 源-监听器模式（Source/Listener）
> 4. 从属者模式（Dependents）

观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一主题对象，当主题对象状态发生变化时，所有的观察者都会感知这一变化。不过需要注意的是，这里的感知并不是观察者主动去监听事件的变化，而是当主题的状态发生变化时，主动调用了注册在主题中的所有观察者的状态更新方法，以此来达到当主题状态变化时，观察者可以做出响应的效果。

观察者模式角色定义：

- 抽象主题角色

主题角色把所有观察者对象的引用存储在一个集合中，并提供接口来支持增加或删除观察者。

```java
/**
 * 抽象主题
 *
 * @author zhenchao.wang 2017-04-21 16:04
 * @version 1.0.0
 */
public abstract class AbstractSubject {

    /**
     * 注册观察者
     *
     * @param observer
     */
    public abstract void attach(AbstractObserver observer);

    /**
     * 注销观察者
     *
     * @param observer
     */
    public abstract void detach(AbstractObserver observer);

    /**
     * 通知在册的所有观察者
     */
    protected abstract void notifyObservers();
}
```

- 具体主题角色

将主题的状态通知到所有具体观察者对象，当具体主题的状态发生改变时，给所有注册的观察者发送通知。

```java
/**
 * 具体主题
 * 考虑到大部分主题对于下面这三个方法的实现都几乎相同，所以这些方法可以抽象到抽象主题中实现
 *
 * @author zhenchao.wang 2017-04-21 16:09
 * @version 1.0.0
 */
public class ConcreteSubject extends AbstractSubject {

    private List<AbstractObserver> observers = new LinkedList<>();

    @Override
    public void attach(AbstractObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(AbstractObserver observer) {
        observers.remove(observer);
    }

    @Override
    protected void notifyObservers() {
        for (final AbstractObserver observer : observers) {
            observer.update();
        }
    }

}
```

- 抽象观察者角色

为所有的具体观察者定义一个统一接口，在收到主题状态变更通知时更新自己

```java
/**
 * 抽象观察者
 *
 * @author zhenchao.wang 2017-04-21 16:07
 * @version 1.0.0
 */
public abstract class AbstractObserver {

    /**
     * 收到通知后触发的更新操作
     */
    public abstract void update();
}
```

- 具体观察者角色

实现抽象观察者定义，如有需要，具体观察者可以持有一个指向具体主题对象的引用。

```java
/**
 * 具体观察者
 *
 * @author zhenchao.wang 2017-04-21 16:16
 * @version 1.0.0
 */
public class ConcreteObserver extends AbstractObserver {

    @Override
    public void update() {
        System.out.println(this.getClass() + " is notified~");
    }
}
```

对于具体主题来说，需要实现增加、删除观察者，以及广播消息的逻辑，考虑到很多具体主题在这一块的实现几乎相同，所以可以将这部分逻辑抽象到抽象主题中。

Java为观察者模式提供了原生支持，jdk内置类抽象观察者类 `java.util.Observer` 和抽象主题类 `java.util.Observable`，这两个类的定义如下：

- java.util.Observer

```java
public interface Observer {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the <code>notifyObservers</code> method.
     */
    void update(Observable o, Object arg);
}
```

- java.util.Observable

```java
public class Observable {
    
    /** 状态是否发生变化 */
    private boolean changed = false;
    
    /** 存放观察者 */
    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    /**
     * 注册一个观察者
     */
    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    /**
     * 注销一个观察者
     */
    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    /**
     * 通知所有的观察者
     */
    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * 通知所有的观察者
     */
    public void notifyObservers(Object arg) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }

    /**
     * 注销所有的观察者
     */
    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    /**
     * 设置已变化状态为true
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * 设置已变化状态为false
     */
    protected synchronized void clearChanged() {
        changed = false;
    }

    /**
     * 检测对象状态是否有变化
     */
    public synchronized boolean hasChanged() {
        return changed;
    }

    /**
     * 获取在册的观察者的数目
     */
    public synchronized int countObservers() {
        return obs.size();
    }
}
```

基于 java 原生观察者模式的例子：

- 具体主题：继承java.util.Observable

```java
/**
 * 基于java原生支持的具体主题实现
 *
 * @author zhenchao.wang 2017-04-21 16:28
 * @version 1.0.0
 */
public class ConcreteSubject extends Observable {

    private String data = StringUtils.EMPTY;

    /**
     * 改变数据值
     *
     * @param data
     */
    public void changeData(String data) {
        if (!StringUtils.equals(this.data, data)) {
            this.data = data;
            this.setChanged();
        }
        this.notifyObservers();
    }

    public String getData() {
        return data;
    }
}
```

- 具体观察者：实现java.util.Observer

```java
/**
 * 基于java原生支持的具体观察者
 *
 * @author zhenchao.wang 2017-04-21 16:26
 * @version 1.0.0
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update(Observable subject, Object arg) {
        System.out.println(this.getClass().getClass() + ", data changed : " + ((ConcreteSubject)subject).getData());
    }

}
```

- 客户端调用

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-21 16:32
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver());
        subject.addObserver(new ConcreteObserver());
        subject.changeData("aaa");
        subject.changeData("bbb");
        subject.changeData("ccc");
    }

}
```