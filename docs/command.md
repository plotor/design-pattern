### 命令模式

命令（Command）模式通过将一个请求（命令）封装为一个对象，从而实现使用不同的命令对客户进行参数化，对请求排队或记录请求日志，以及支持撤销操作（可选）。

> __什么是对客户进行参数化？__
>
> 即客户无需知晓命令的具体执行细节，只要知道如何触发命令即可。以按键为例，具体按键后面对应的功能是由与该按键绑定的命令决定的。同样是按键操作，触发的行为可能大相径庭，用户只需要知道如何按下按键，以及按键按下之后的效果，具体按键后面对应的命令如何执行则对用户透明。

要理解命令模式，首先我们的焦点应该集中在命令两个字身上。命令的汉语解释为：命令（令）是国家行政机关及其领导人发布的指挥性和强制性的公文。说白了，命令的发生需要 __发令者__ 和 __执行者__。而且发令者和执行者各司其职，发令者只负责发令，甚至他自己都可以不知道这条命令具体该如何执行，以及由谁执行，而执行者只负责执行这条命令，他也不需要知道如何发令，很多时候他也没有资格去发令。

以简单的电脑开机为例，人扮演的是发令者的角色，而电脑上的开机组件扮演的是命令执行者角色。人不需要去知道开机这条命令在电脑内部具体如何执行，只需要知道按一下开机键即可，具体的开机操作由电脑开机组件去完成，这样就达到了发令者和执行者之间解耦的效果。人知道如何发令之后，可以通过按一下按键来执行更多其它操作，比如打开电视、启动汽车，或是切换空调模式等。人只需要知道按一下键来发号施令，而具体的操作由相应的命令接收者来完成。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
命令接口 | Command | 接口 | 声明执行命令的方法
具体命令 | ConcreteCommand | 类 | 具体命令实现，一般需要持有一个命令接收者对象，并调用命令接收者相应的方法来完成命令的执行，本身不负责实现命令的执行逻辑
接收者 | Receiver | 类 | 也叫执行者，真正执行命令的对象
发令者 | Invoker | 类 | 发号施令要求命令对象执行请求，一般可以维护多个命令对象，是客户端触发命令执行的入口

示例实现：

- 命令接口

```java
public interface Command {

    /**
     * 执行命令对应的操作，命令本身不负责执行，而是调用 Receiver 执行
     */
    void execute();

}
```

- 具体命令

```java
public class ConcreteCommand implements Command {

    /** 命令接收和执行者 */
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        // 为当前命令绑定一个执行者
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        // 通过调用执行者相应的方法执行命令
        receiver.action();
    }

}
```

- 接收者（执行者）

```java
public class Receiver {

    public void action() {
        // 真正执行命令操作
    }

}
```

- 发令者

```java
public class Invoker {

    /** 持有的命令对象 */
    private Command command;

    /**
     * 发起命令执行请求
     */
    public void runCommand() {
        command.execute();
    }

    public Invoker setCommand(Command command) {
        this.command = command;
        return this;
    }

}
```

#### 示例

下面以电脑开机的场景为例，当我们希望启动一台电脑时，我们只需要按一下机箱（Invoker）上的开机键即可，主板（Receiver）在接收到来自机箱的开机命令（Command）之后会完成相应的开机操作。在这个过程中，我们并不知道开机命令是如何被主板执行的，主板也不知道我们是怎么触发开机的。具体实现：

- 命令：开机命令

```java
public interface Command {

    /**
     * 执行命令
     */
    void execute();

}

public class BootCommand implements Command {

    /** 主板对象 */
    private MotherBoard motherBoard;

    public BootCommand(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    @Override
    public void execute() {
        // 调用主板接口执行开机操作
        motherBoard.boot();
    }

}
```

- 接收者：主板

```java
public interface MotherBoard {

    /**
     * 开机
     */
    void boot();

}

public class MsiMotherBoard implements MotherBoard {

    @Override
    public void boot() {
        System.out.println("欢迎使用微星主板，请稍后...");
        System.out.println("硬件自检中");
        System.out.println("加载操作系统");
        System.out.println("启动成功，欢迎使用");
    }

}
```

- 发令者：机箱

```java
public class BoxInvoker {

    private Command bootCommand;

    /**
     * 开机按钮，供用户使用
     */
    public void powerOn() {
        bootCommand.execute();
    }

    public BoxInvoker setBootCommand(Command bootCommand) {
        this.bootCommand = bootCommand;
        return this;
    }

}
```

- 客户端

```java
// 组合命令和执行者
MotherBoard motherBoard = new MsiMotherBoard();
Command bootCommand = new BootCommand(motherBoard);

// 为机箱上的按钮设置命令
BoxInvoker box = new BoxInvoker();
box.setBootCommand(bootCommand);

// 按下开机键开机
box.powerOn();
```

由上述实现可以看到，命令本身是不负责执行操作，真正执行则交由 Receiver 完成。Invoker 在整个过程中用于发号施令，是与客户端交互的对象。
