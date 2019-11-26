### 外观模式

外观（Facade）模式，也叫门面模式，用于为子系统中的一组接口提供一个一致的界面，通过定义高层次的 API 来简化子系统的使用。这里的界面是指从一个组件的外部来看该组件所能看到的东西，也就是该组件的外观。对于一个类而言，我们能够从外面看到的是一个类所有的 public 方法，这些 public 方法构成了这个类的外观。

外观模式通过引入一个包含简单 API 的外观类，并由外观类在这些简单 API 的实现中调用各个子系统，从而屏蔽内部的复杂逻辑，对于客户端而言，在与整个复杂系统的交互上要简单很多，因为客户端只能看到这个简单的外观类，并且能够基于该外观类与后面复杂的系统进行交互。

外观模式的优点如下：

- __松散耦合__：通过外观类解耦了客户端与具体子系统之间的耦合关系，让子系统内部的模块更容易扩展和维护。
- __简单易用__：外观类让子系统更加易于使用，客户端不再需要了解子系统的各个模块，相当于对外界提供了一站式的服务。
- __更好地划分访问层次__：一个系统的各个方法，有些是暴露对外的，而有些是仅供内部使用的，将对外的接口集中到外观类中，这样既可以简化客户端的使用，也能更好地隐藏内部实现细节。

需要注意的一点是，如果一个外观类不能够将子系统所有的功能提供给外界，那么可以通过修改或继承外观类的方法来将子系统的全部功能提供给外界。但是，如果一个子系统没有某个功能，寄希望通过修改或者继承外观类来提供这个新的功能是不可以的。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
外观（门面） | Facade | 类 | 代表构成系统的各个模块的简单外观（门面），提供高层次 API，以简化系统的使用
系统子模块 | | 类 | 外观类负责调用这些子模块，但是这些子模块并不知道外观类的存在
客户端 | Client | 类 | 使用外观类与系统进行交互

#### 示例

说到外观模式，突然想起了 2009 年入手的一代神机 Y450。Y450 有一个功能叫 “一键影音”，就是一键开启适合影音娱乐的效果。当按下对应的按键之后，音效会变的动听，屏幕色彩也会变得艳丽，并打开一些其它的效果等，这就是门面模式的一个生动应用场景。

设想如果没有这个 “一键影音” 键，那么希望达到这样的效果，我们需要分别控制各个开关，比如设置音效，调节音量，调整屏幕色彩、对比度等，而正是在这里引入了外观模式，让我们可以简单、快捷的达到我们的目的。

- 系统子模块：音响效果

```java
public interface SoundEffect {
    void turnOn();
    void turnOff();
}

// 古典音效
public class ClassicalSoundEffect implements SoundEffect {

    @Override
    public void turnOn() {
        System.out.println("Turn on classical sound effect!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn on classical sound effect!");
    }

}

// 流行音效
public class FashionSoundEffect implements SoundEffect {

    @Override
    public void turnOn() {
        System.out.println("Turn on fashion sound effect!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off fashion sound effect!");
    }

}
```

- 系统子模块：屏幕色调

```java
public interface ScreenTone {
    void turnOn();
    void turnOff();
}

// 自然色调
public class NaturalScreenTone implements ScreenTone {

    @Override
    public void turnOn() {
        System.out.println("Turn on natural screen tone!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off natural screen tone!");
    }

}

// 艳丽色调
public class GorgeousScreenTone implements ScreenTone {

    @Override
    public void turnOn() {
        System.out.println("Turn on gorgeous screen tone!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off gorgeous screen tone!");
    }

}
```

- 外观（门面）：一键影音

```java
public class OneKeyTheaterFacade {

    private SoundEffect soundEffect;
    private ScreenTone screenTone;

    public OneKeyTheaterFacade() {
        this.soundEffect = new FashionSoundEffect();
        this.screenTone = new GorgeousScreenTone();
    }

    public void turnOn() {
        this.soundEffect.turnOn();
        this.screenTone.turnOn();
    }

    public void turnOff() {
        this.soundEffect.turnOff();
        this.screenTone.turnOff();
    }

}
```

- 客户端

```java
// 原始调用方式
SoundEffect soundEffect = new FashionSoundEffect();
soundEffect.turnOn();
ScreenTone screenTone = new GorgeousScreenTone();
screenTone.turnOn();

// 采用外观模式
OneKeyTheaterFacade facade = new OneKeyTheaterFacade();
facade.turnOn();
```

由上面的示例我们可以看到外观模式的主要作用是对外暴露一个简单的 API，以屏蔽底层系统复杂的 API，最终目的是简化客户端对于复杂系统的使用。
