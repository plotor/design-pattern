## 门面模式（也叫外观模式）

> 英文名称 ： Facade

门面模式，也叫外观模式。

说到外观模式，突然想起了09年入手的一代神机“Lenovo Y450”。Y450有一个功能叫“一键影音”，就是一键开启适合影音娱乐的效果，当按下之后，音效会变的动听，屏幕色彩也会变得艳丽，还有打开其它的一些效果等等，这就是门面模式的一个生动实现。设想如果没有这个“一键影音”键，那么希望达到这样的效果，我们需要分别控制各个开关，比如设置音效，调节音量，调整屏幕色彩、对比度等等，而正是在这里引入了门面模式，让我们可以简单、快捷的达到我们的目的。

代码实现：

- 音效模块

```java
/**
 * 音效
 *
 * @author zhenchao.wang 2017-04-08 22:56
 * @version 1.0.0
 */
public interface AbstractSoundEffect {

    /**
     * 开启
     */
    void turnOn();

    /**
     * 关闭
     */
    void turnOff();

}

/**
 * 古典音效
 *
 * @author zhenchao.wang 2017-04-08 23:01
 * @version 1.0.0
 */
public class ClassicalSoundEffect implements AbstractSoundEffect {

    @Override
    public void turnOn() {
        System.out.println("Turn on classical sound effect!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn on classical sound effect!");
    }

}

/**
 * 流行音效
 *
 * @author zhenchao.wang 2017-04-08 22:59
 * @version 1.0.0
 */
public class FashionSoundEffect implements AbstractSoundEffect {

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

- 屏幕色调模块

```java
/**
 * 屏幕色调
 *
 * @author zhenchao.wang 2017-04-08 23:03
 * @version 1.0.0
 */
public interface AbstractScreenTone {

    /**
     * 开启
     */
    void turnOn();

    /**
     * 关闭
     */
    void turnOff();

}

/**
 * 自然色调
 *
 * @author zhenchao.wang 2017-04-08 23:04
 * @version 1.0.0
 */
public class NaturalScreenTone implements AbstractScreenTone {

    @Override
    public void turnOn() {
        System.out.println("Turn on natural screen tone!");
    }

    @Override
    public void turnOff() {
        System.out.println("Turn off natural screen tone!");
    }

}

/**
 * 艳丽色调
 *
 * @author zhenchao.wang 2017-04-08 23:06
 * @version 1.0.0
 */
public class GorgeousScreenTone implements AbstractScreenTone {

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

- 客户端调用

```java
/**
 * 客户端调用
 *
 * @author zhenchao.wang 2017-04-08 23:12
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        // 原始调用方式
        AbstractSoundEffect soundEffect = new FashionSoundEffect();
        soundEffect.turnOn();
        AbstractScreenTone screenTone = new GorgeousScreenTone();
        screenTone.turnOn();

        // 采用门面模式
        OneKeyTheaterFacade facade = new OneKeyTheaterFacade();
        facade.turnOn();
    }

}
```

由上面的例子我们可以看到门面模式的主要作用是对外暴露一个简单的接口，来屏蔽底层复杂的调用，其优点如下：

- __松散耦合__

通过门面解耦了客户端与具体子系统之间的耦合关系，让子系统内部的模块更容易扩展和维护。

- __简单易用__

门面让子系统更加易于使用，客户端不再需要了解子系统的各个模块，相当于对外界提供了一站式的服务。

- __更好地划分访问层次__

一个系统的各个方法，有些是暴露对外的，而有些是仅供内部使用的，将对外的接口集中到门面中，这样既可以简化客户端的使用，也能更好地隐藏内部实现细节。

此外，还需要注意的一点是：

> 如果一个门面模式不能够将子系统所有的功能提供给外界，那么可以通过修改或继承门面类的方法来将子系统的全部功能提供给外界。但是如果一个子系统没有某个功能，寄希望通过修改或者继承门面类来提供这个新的功能是不可以的。