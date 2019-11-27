### 备忘录模式

备忘录（Memento）模式是指在不破坏封装性的前提下捕获一个对象的内部状态，并在该对象之外保存该状态，从而方便在未来将对象恢复到该状态。一个备忘录就是一个对象，用于存储另外一个对象在某个瞬间的内部状态。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
生成者 | Originator | 类 | 使用备忘录保存和恢复自己的运行状态
备忘录 | Memento | 类 | 存储 Originator 的内部状态，具体需要存储哪些状态由 Originator 决定，并且只允许 Originator 访问其内部数据，以保证数据的安全性
管理者 | Caretaker | 类 | 管理备忘录对象，提供对备忘录对象的保存和获取功能，但不能修改备忘录对象中的数据

##### 宽接口和窄接口

Memento 在具体实现上分为宽接口和窄接口两种。

__宽接口__ 是指 Memento 在定义上包含所有用于获取 Originator 对象状态信息的方法，因此会暴露所有 Memento 对象的内部数据，所以能够使用宽接口 Memento 的只有 Originator 角色。__窄接口__ 是指 Memento 在定义上对外部角色仅提供有限的功能（通常只是一个表接口，什么也看不见），有效防止内部信息泄露。

具体实现上，我们一般将 Memento 设计成一个标记接口，并在 Originator 内部提供 MementoImpl 内部类实现。

##### 各角色实现

- 备忘录

```java
public interface Memento {
    /* 备忘录窄接口 */
}
```

- 生成者

```java
public class Originator {

    // 以内部类的方式实现
    private static class MementoImpl implements Memento {

        private String state;

        MementoImpl(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    // 生成者状态信息
    private String state;

    private Memento createMemento() {
        // 生成备忘录对象
        return new MementoImpl(state);
    }

    private void restoreFromMemento(Memento memento) {
        // 从备忘录对象中恢复状态
        MementoImpl mementoImpl = (MementoImpl) memento;
        this.state = mementoImpl.getState();
    }

}
```

- 管理者

```java
public class Caretaker {

    private Memento memento;

    public void saveMemento(Memento memento) {
        // 保存备忘录对象
        this.memento = memento;
    }

    public Memento retriveMemento() {
        // 获取被保存的备忘录对象
        return this.memento;
    }

}
```

#### 示例

考虑一个掷骰子游戏的例子，如果掷出的骰子值为奇数则金币数加 100，如果值为 2 或 4 则金币数目减半，否则什么也不做。为了保证金币数的最大化，我们设计了一个外挂程序，当骰子值不为奇数时就将金币数恢复到上次的值，避免金币数减少。所以我们需要一个备忘录，当金币数增加时记录当前游戏的状态信息，实现如下（其中 Memento 接口、Caretaker 的实现同上）：

- Memento 和 Originator 实现

```java
public class Gamer {

    private static class MementoImpl implements Memento {

        private int money;

        MementoImpl(int money) {
            this.money = money;
        }

        int getMoney() {
            return money;
        }
    }

    /** 持有的金钱数目 */
    private int money;

    public Gamer(int money) {
        this.money = money;
    }

    public boolean play() {
        // 掷骰子
        int dice = RandomUtils.nextInt(1, 7);
        if (dice % 2 == 1) {
            money += 100;
            System.out.println("金钱增加 100，额度： " + money);
            return true;
        } else if (dice == 2 || dice == 4) {
            money /= 2;
            System.out.println("金钱减半，额度： " + money);
        } else {
            System.out.println("什么都没有发生，额度： " + money);
        }
        return false;
    }

    public Memento createMemento() {
        // 创建备忘录对象
        return new MementoImpl(money);
    }

    public void restoreMemento(Memento memento) {
        // 从备忘录中恢复状态
        MementoImpl mementoImpl = (MementoImpl) memento;
        this.money = mementoImpl.getMoney();
    }

    public int getMoney() {
        return money;
    }

}
```

- 客户端

```java
Gamer gamer = new Gamer(100);
Caretaker caretaker = new Caretaker();
// 保存初始状态
caretaker.saveMemento(gamer.createMemento());
for (int i = 0; i < 10; i++) {
    if (gamer.play()) {
        // 持有的金钱增多了，保存游戏状态
        Memento memento = gamer.createMemento();
        caretaker.saveMemento(memento);
    } else {
        // 持有的金钱变少或者没变，恢复到之前的状态
        Memento memento = caretaker.retriveMemento();
        gamer.restoreMemento(memento);
    }
    TimeUnit.SECONDS.sleep(1L);
}
System.out.println("最终的金额： " + gamer.getMoney());
```
