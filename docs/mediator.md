### 中介者模式

中介者（Mediator）模式使用一个中介对象封装一系列对象间的交互，使得各对象之间不需要显式的相互引用，从而实现松耦合，并能够支持独立修改对象与中介者之间的交互。在中介者模式中，所有参与的对象都只和中介对象进行交互，当某个参与对象发生变化时只需要修改中介者即可，其它对象则无感知。

参与对象与中介者的交互形式上是可以视具体情况而定的，可以是方法调用、回调通知、RPC，亦或是消息机制等。

中介者模式的优先如下：

1. __松耦合__：通过把多个参与对象之间的交互封装到中介者对象中，使得参与对象之间松耦合，基本上可以做到互不依赖，从而实现参与对象可以独立的变化和复用，避免牵一发而动全身。
2. __集中控制交互__：参与对象之间的交互被封装到中介者对象中集中管理，使得当交互行为发生变化时只需要修改中介者对象即可。
3. __多对多变成一对多__：不引入中介者模式则参与对象之间的交互是多对多的，中介者让这种多对多的交互变成为一对多的交互，从而使得参与对象之间的关系更加容易理解和实现。

当然，过度集中化使得中介者对象变得十分复杂，难以维护，这也是中介者模式的缺点。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
中介者 | Mediator | 抽象类 / 接口 | 定义各个参与对象之间交互的方法
具体中介者 | ConcreteMediator | 类 | 具体中介者实现，需要了解并维护各个参与对象，并负责协调各个参与对象之间的交互关系
参与对象 | Colleague | 抽象类 | 负责约束参与对象的类型，并实现一些公共的功能
具体参与对象 | ConcreteColleague | 类 | 具体参与对象实现，与中介者进行交互

实际开发中通常可以去掉 Colleague 抽象父类，让任意对象可以相互交互。也可以不定义 Mediator 接口，将具体的中介者对象实现成为单例，这样就可以让具体参与对象无需持有中介者对象。中介者对象也可以不持有具体的参与对象，可以在需要时创建、获取，或者从参数传入等。经过这样的修改之后，中介者就变成了广义上的中介者。

各角色实现如下：

- 中介者

```java
public interface Mediator {

    /**
     * 参与对象在状态发生改变时通知中介者，让中介者去负责与其它参与对象进行交互
     *
     * @param colleague
     */
    void changed(Colleague colleague);

}
```

- 具体中介者

```java
public class ConcreteMediator implements Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    @Override
    public void changed(Colleague colleague) {
        // 某个参与对象状态发生变化，一般需要与其它参与对象进行交互
    }

    public ConcreteMediator setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
        return this;
    }

    public ConcreteMediator setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
        return this;
    }

}
```

- 参与对象

```java
public abstract class Colleague {

    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

}
```

- 具体参与对象

```java
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        // 需要与其它参与对象进行通信，通知中介者
        this.getMediator().changed(this);
    }

}

public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        // 需要与其它参与对象进行通信，通知中介者
        this.getMediator().changed(this);
    }

}
```

#### 示例

以播放 CD 这件小事为例，当我们将 CD 插入光驱之后，光驱需要读取数据，然后交给 CPU 处理，最后让显卡显示影像，声卡播放声音。这个一系列组件的交互并不是直接的，而是由主板这个中介者统筹完成的，组件之间并不知晓彼此的存在。所以这是一个典型的中介者模式引用，具体实现如下：

参与对象 Colleague 和中介者 Mediator 的定义同上，这里不再重复，下面来看一下具体参与对象和具体中介者的实现。

- 具体参与对象

```java
public class CD extends Colleague {

    private String data = "";

    public CD(Mediator mediator) {
        super(mediator);
    }

    public void read() {
        this.data = "design-pattern, the art of programming";
        // 读取数据成功，通知主板
        this.getMediator().changed(this);
    }

    public String getData() {
        return data;
    }

}

public class CPU extends Colleague {

    private String videoData;
    private String soundData;

    public CPU(Mediator mediator) {
        super(mediator);
    }

    public void execute(String data) {
        String[] items = data.split(",\\s*");
        this.videoData = items[0];
        this.soundData = items[1];
        // 通知主板，处理数据完成
        this.getMediator().changed(this);
    }

    public String getVideoData() {
        return videoData;
    }

    public String getSoundData() {
        return soundData;
    }

}

public class VideoCard extends Colleague {

    public VideoCard(Mediator mediator) {
        super(mediator);
    }

    public void display(String data) {
        System.out.println("播放视频： " + data);
    }

}

public class SoundCard extends Colleague {

    public SoundCard(Mediator mediator) {
        super(mediator);
    }

    public void display(String data) {
        System.out.println("播放声音： " + data);
    }

}
```

- 具体中介者

```java
public class MotherBoard implements Mediator {

    private CD cd;
    private CPU cpu;
    private VideoCard videoCard;
    private SoundCard soundCard;

    @Override
    public void changed(Colleague colleague) {
        if (colleague == cd) {
            this.processData(cd);
        } else if (colleague == cpu) {
            this.processData(cpu);
        } else {
            throw new IllegalStateException("unknown colleague: " + colleague);
        }
    }

    private void processData(CD cd) {
        // 从光驱读取数据
        String data = cd.getData();
        // 将数据交给 CPU 处理
        cpu.execute(data);
    }

    private void processData(CPU cpu) {
        // 获取 CPU 处理后的数据
        String videoData = cpu.getVideoData();
        String soundData = cpu.getSoundData();
        // 将数据分别传递给显卡和声卡
        videoCard.display(videoData);
        soundCard.display(soundData);
    }

    // ... setter

}
```

- 客户端

```java
MotherBoard mediator = new MotherBoard();

CD cd = new CD(mediator);
CPU cpu = new CPU(mediator);
VideoCard vc = new VideoCard(mediator);
SoundCard sc = new SoundCard(mediator);

mediator.setCd(cd);
mediator.setCpu(cpu);
mediator.setVideoCard(vc);
mediator.setSoundCard(sc);

cd.read();
```
