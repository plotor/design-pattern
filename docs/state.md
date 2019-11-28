### 状态模式

状态（State）模式允许一个对象在内部状态发生变化时改变其行为（即状态决定行为），其目的是为了将状态和行为想分离，通过切换状态来改变对象的行为。在状态模式中，上下文是持有状态的对象，但是上下文对象本身并不负责处理跟状态相关的行为，而是将具体的处理逻辑委托给状态对应的状态处理类完成。客户端一般只与上下文对象交互，不应该具备访问状态对象的权限，更不应该参与维护状态。

状态的转换控制可以由上下文对象负责完成，也可以由状态对象自己负责完成。如何选择呢？

- 如果状态转换的规则是一定的，一般不需要进行扩展操作，则适合在上下文中对象统一进行维护。
- 如果状态的转换取决于前一个状态的处理结果，或者是依赖于外部数据，则适合在状态对象中统一进行维护。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
状态 | State | 接口 / 抽象类 | 用于封装上下文中一个特定状态所对应的行为
具体状态 | ConcreteState | 类 | 具体状态实现
上下文 | Context | 类 | 用来定义客户端感兴趣的 API，同时维护一个具体处理当前状态的 State 对象，但是本身不负责处理与状态相关的逻辑

示例实现：

- 状态

```java
public interface State {

    /**
     * 状态处理函数
     *
     * @param params 示例参数
     */
    void handle(String params);

}

public class ConcreteState1 implements State {

    @Override
    public void handle(String params) {
        // 具体处理逻辑，可以在此切换状态
    }

}

public class ConcreteState2 implements State {

    @Override
    public void handle(String params) {
        // 具体处理逻辑，可以在此切换状态
    }

}
```

- 上下文

```java
public class Context {

    private State state;

    /**
     * 用户感兴趣的方法
     *
     * @param params
     */
    public void request(String params) {
        // 调用 State 的方法处理
        state.handle(params);
    }

    public Context setState(State state) {
        this.state = state;
        return this;
    }

}
```

#### 示例

考虑一个糖果自动售卖机的例子，用户如果需要获取糖果则需要先投币，然后机器才能输出糖果。糖果自动售卖机一般对应如下 4 个状态：

1. 没有糖果：卖完了，缺货。
2. 拥有钱币：说明这时用户希望购买糖果，已经投入了钱币，但是还没有出货。
3. 没有钱币：用户未投入钱币，或者交易已完成。
4. 输出糖果：正在完成交易。

基于状态模式的实现如下：

- 状态

```java
public abstract class State {

    // 糖果机对象
    protected CandyMachine candyMachine;

    public State(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    // 投币
    public abstract void putInCoins();

    // 退币
    public abstract void refundCoins();

    // 按下出货按钮
    public abstract void clickButton();

    // 出货
    public abstract void outputCandy();

}
```

- 具体状态

```java
public class NoCandyState extends State {

    public NoCandyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("对不起，糖果已卖完！");
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，您还没有投入钱币！");
    }

    @Override
    public void clickButton() {
        System.out.println("对不起，糖果已卖完！");
    }

    @Override
    public void outputCandy() {
        System.out.println("对不起，糖果已卖完！");
    }

}

public class NoMoneyState extends State {

    public NoMoneyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        // 已投币，切换状态
        candyMachine.switchState(candyMachine.getHasMoneyState());
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，您还没有投币！");
    }

    @Override
    public void clickButton() {
        System.out.println("对不起，您还没有投币！");
    }

    @Override
    public void outputCandy() {
        System.out.println("对不起，您还没有投币！");
    }

}

public class HasMoneyState extends State {

    public HasMoneyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("您已投入钱币，无需再继续投入！");
    }

    @Override
    public void refundCoins() {
        System.out.println("系统正在退款，请稍等...");
        // 用户要求退款，退款完成后切换状态
        candyMachine.switchState(candyMachine.getNoMoneyState());
    }

    @Override
    public void clickButton() {
        System.out.println("正在为您准备糖果...");
        // 用户已投币，要求出货
        candyMachine.switchState(candyMachine.getBaleCandyState());
    }

    @Override
    public void outputCandy() {
        System.out.println("请先按下按钮！");
    }

}

public class BaleCandyState extends State {

    public BaleCandyState(CandyMachine candyMachine) {
        super(candyMachine);
    }

    @Override
    public void putInCoins() {
        System.out.println("系统正在为您打包糖果，请稍后再投币！");
    }

    @Override
    public void refundCoins() {
        System.out.println("对不起，本次消费已生效，无法退币！");
    }

    @Override
    public void clickButton() {
        System.out.println("系统正在为您打包糖果，无需再按按钮！");
    }

    @Override
    public void outputCandy() {
        candyMachine.decreaseCount();
        System.out.println("糖果打包成功，请取走！");
        if (candyMachine.getCount() > 0) {
            // 还有剩余糖果
            candyMachine.switchState(candyMachine.getNoMoneyState());
        } else {
            // 糖果已卖完
            candyMachine.switchState(candyMachine.getNoCandyState());
        }
    }

}
```

- 上下文：糖果自动售卖机

```java
public class CandyMachine {

    private NoCandyState noCandyState;
    private BaleCandyState baleCandyState;
    private NoMoneyState noMoneyState;
    private HasMoneyState hasMoneyState;

    private int count;
    private State state;

    public CandyMachine(int count) {
        this.count = count;
        this.noCandyState = new NoCandyState(this);
        this.baleCandyState = new BaleCandyState(this);
        this.noMoneyState = new NoMoneyState(this);
        this.hasMoneyState = new HasMoneyState(this);
        // 设置初始状态
        this.state = this.count > 0 ? this.noMoneyState : this.noCandyState;
    }

    public void putInCoins() {
        state.putInCoins();
    }

    public void refundCoins() {
        state.refundCoins();
    }

    public void clickButton() {
        state.clickButton();
    }

    public void outputCandy() {
        state.outputCandy();
    }

    public void decreaseCount() {
        this.count--;
    }

    public CandyMachine switchState(State state) {
        // 切换状态
        this.state = state;
        return this;
    }

    // ... getter

}
```
