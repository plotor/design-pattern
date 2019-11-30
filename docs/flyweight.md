### 享元模式

- [单纯享元模式](#单纯享元模式)
    - [角色](#角色)
    - [示例](#示例)
- [复合享元模式](#复合享元模式)
    - [角色](#角色)
    - [示例](#示例)

---

享元（Flyweight）模式是以共享的方式有效地避免创建大量的细粒度对象，其关键是区分内部状态（Internal State）和外部状态（External State）。其中 __内部状态__ 是指存储在享元对象内部，且不会随环境改变的状态；__外部状态__ 是指随环境改变，不可以共享的状态。外部状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候以参数形式传入到享元对象内部。外部状态不可以影响享元对象的内部状态，即外部状态和内部状态是独立的。

享元模式的核心就是将内部状态分离出来进行共享，称之为享元。通过共享享元对象来减少内存占用，并将外部状态分离出来，放到外部让客户端去维护，在需要的时候传递给享元对象使用。

享元模式能够大幅度降低内存中的细粒度对象数目，但在实现上让系统变得更加复杂，并且将享元独享的状态外部化，相对更加耗时，所以在引入享元模式时需要斟酌其优缺点。满足下列所有条件时可以考虑使用享元模式：

1. 一个系统有大量的细粒度对象；
2. 这些对象消耗着大量的内存；
3. 这些对象中的大部分都可以外部化；
4. 这些对象可以按照内部状态分成很多的组，当把外部对象从对象中剔除时，每一个组都可以仅用一个对象表示；
5. 软件对象不依赖于这些对象的身份，即这些对象是不可分辨的。

享元模式分为 __单纯享元模式__ 和 __复合享元模式__。

#### 单纯享元模式

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
享元 | Flyweight | 接口 | 接收外部状态对象，在具体享元对象中可能会使用这些外部数据
具体享元 | ConcreteFlyweight | 类 | 具体享元对象，需要封装内部状态，该对象必须是可共享的，但是不要求一定被共享
享元工厂 | FlyweightFactory | 类 | 用于创建和管理享元对象，并对外提供获取享元对象的 API，客户端不应该直接创建享元对象
客户端 | Client | 类 | 持有一个 Flyweight 引用，计算或存储享元对象的外部状态

示例实现：

- 享元

```java
public interface Flyweight {

    /**
     * 操作函数
     *
     * @param externalState 外部状态
     */
    void operate(Object externalState);

}
```

- 具体享元

```java
public class ConcreteFlyweight implements Flyweight {

    /** 内部状态 */
    private Object internalState;

    public ConcreteFlyweight(Object internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operate(Object externalState) {
        // 具体处理逻辑，可能会用到享元的内部、外部状态
    }

}
```

- 享元工厂

```java
public class FlyweightFactory {

    private Map<String, Flyweight> map = new ConcurrentHashMap<>();

    private static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取享元对象
     *
     * @param key
     * @return
     */
    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = map.get(key);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight("some internal state");
            map.put(key, flyweight);
        }
        return flyweight;
    }

}
```

##### 示例

最近在看游客帐号项目代码的时候，发现之前构造的职责链像个楼梯台阶一样的堆在那里，很是影响代码的美观性。并且，一条链上的多个对象在每次请求时都需要创建一遍，对于一个高并发的项目来说是一笔不小的开销，于是想对这一块的代码进行优化，而享元模式刚好满足需求。

游客帐号是我们常见的一种帐号类型，考虑到用户名密码的登录方式门槛较高，很多用户不愿意去登录，所以对于一些安全性要求不高的业务来说，我们可以为用户生成游客帐号。比如在该项目中，选择了一些元素包括设备 IMEI、FID 等信息生成游客帐号，如果实在没有可以参考的因素，则生成随机的游客帐号。在代码层面，针对每一种类型的帐号设计了相应的处理器，并且每个处理器都可以设置降级委托处理器。如何当前处理器处理失败，可以触发降级策略。如果不用享元模式，那么每次来一个请求，我都需要根据具体的类型创建相应的处理器对象，考虑到游客帐号的请求量一般较大，所以有必要尽量避免创建一些不必要的对象。

我们先来分析一下这里面哪些是内部状态，哪些是外部状态，因为享元模式的核心就是要将内部状态和外部状态区分开来，将内部状态封装在享元内部实现共享，而外部状态则有客户端传递进来。很显然，游客帐号生成的参考元素是外部状态，因为每一个设备都有不同的设备 IMEI 信息，有的设备甚至还有安全芯片，这些东西必须由外面传递进来，而一个处理器的降级处理器则是内部状态，因为降级处理器一旦设定，不会动态变化。具体实现如下：

- 游客类型

```java
public enum VisitorType {

    /** 默认类型 */
    DEFAULT(0),

    /** 基于安全芯片 */
    FID(1),

    /** 基于设备IMEI信息 */
    DEVICE(2);

    private int id;

    VisitorType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
```

- 享元：游客处理器

```java
public abstract class VisitorFlyweight {

    /**
     * 创建或恢复游客信息
     *
     * @param id
     * @return
     */
    public abstract void createOrRecoverVisitor(String id);

}
```

- 具体享元：各游客类型对应的处理器

```java
// 默认类型游客处理器享元
public class DefaultVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public DefaultVisitorFlyweight() {
    }

    public DefaultVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String id) {
        System.out.println("Create visitor info in default schema!");
    }

}

// FID 类型游客处理器享元
public class FidVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public FidVisitorFlyweight() {
    }

    public FidVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String fid) {
        System.out.println("Create or recover visitor info by fid : " + fid);

        if (null != delegateFlyweight && !this.mockCreateOrRecoverVisitor()) {
            // 调用委托处理器处理
            delegateFlyweight.createOrRecoverVisitor(fid);
        }
    }

    private boolean mockCreateOrRecoverVisitor() {
        // 模拟操作是否成功
        return RandomUtils.nextInt(0, 10) > 5;
    }

}

// DeviceId 类型游客处理器享元
public class DeviceIdVisitorFlyweight extends VisitorFlyweight {

    /** 内部状态：委托处理器 */
    private VisitorFlyweight delegateFlyweight;

    public DeviceIdVisitorFlyweight() {
    }

    public DeviceIdVisitorFlyweight(VisitorFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String deviceId) {
        System.out.println("Create or recover visitor info by device id : " + deviceId);
    }

}
```

- 享元工厂

```java
public class FlyweightFactory {

    private static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private Map<VisitorType, VisitorFlyweight> flyweightMap = new ConcurrentHashMap<>();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return INSTANCE;
    }

    public VisitorFlyweight getFlyweight(VisitorType visitorType) {
        if (null == visitorType) {
            return null;
        }

        // 先从缓存中找
        VisitorFlyweight flyweight = flyweightMap.get(visitorType);
        if (null != flyweight) {
            return flyweight;
        }

        // 缓存不命中，创建享元并更新缓存
        switch (visitorType) {
            case DEFAULT: {
                flyweight = new DefaultVisitorFlyweight();
                flyweightMap.put(VisitorType.DEFAULT, flyweight);
                break;
            }
            case FID: {
                // 设置降级委托处理器
                flyweight = new FidVisitorFlyweight(new DefaultVisitorFlyweight());
                flyweightMap.put(VisitorType.FID, flyweight);
                break;
            }
            case DEVICE: {
                flyweight = new DeviceIdVisitorFlyweight();
                flyweightMap.put(VisitorType.DEVICE, flyweight);
                break;
            }
            default:
                throw new IllegalArgumentException("unknown visitor type: " + visitorType);
        }
        return flyweight;
    }

}
```

- 客户端

```java
FlyweightFactory factory = FlyweightFactory.getInstance();

VisitorFlyweight flyweight = factory.getFlyweight(VisitorType.DEFAULT);
flyweight.createOrRecoverVisitor(randomId());

flyweight = factory.getFlyweight(VisitorType.FID);
flyweight.createOrRecoverVisitor(randomId());

flyweight = factory.getFlyweight(VisitorType.DEVICE);
flyweight.createOrRecoverVisitor(randomId());
```

通过上述代码实现，可以发现享元工厂是整个享元模式实现共享的核心，通过对享元对象的缓存来保证每一个享元都只有一份，不过在实现之前要区分好内部状态和外部状态，不然会存在线程安全问题。

#### 复合享元模式

复合享元由单纯享元复合而成，一般来说复合享元的组成元素是随外部状态而变化的，所以复合享元通常被描述为不可共享的，这里的不可共享是指这个复合享元不可共享，但是组成复合享元的单纯享元对象还是可以共享的，但是我认为如果外部状态的种类是有限的，我们也可以将复合享元设计成可共享的。

##### 角色

复合享元模式相对于单纯享元模式在角色上增加了一个 __复合享元__ 角色。复合享元所代表的对象是不可以共享的（所以也称作不可共享的享元对象），一个复合享元对象可以分解成多个单纯享元对象，复合享元具有以下两个责任：

1. 由单纯的享元对象复合而成，因此需要提供 `add()` 这样的聚集管理方法，一个复合对象具有不同的聚集，并且可以在对象创建之后被修改，所以复合对象的状态是可变的，也就不可被共享。
2. 复合享元对象实现了抽象享元接口。

##### 示例

下面还是以一个自己在项目中遇到的实际例子来举例说明，话说有一天我在看自己以前写的项目代码的时候，看到了下面这样的一坨代码：

```java
// 构建处理器链
AbstractResultHandler resultHandler =
        new VisitorPassTokenCreateHandler(
                new SecurityHintUpdateHandler(
                        new AuthorizationModeHandler(
                                new SuccessResultHandler(), visitorInfo.getVisitorId(), requestParams.getSid()),
                        visitorInfo.getVisitorId(), requestParams.getSid()), visitorInfo);
```

实际上这一坨代码虽然看起来不好看，但是其中的设计还是很灵活的。这里面采用了职责链模式，并允许随机组合各个 handler，但是在构造上采用了静态构造的方式，所以就有了这样一坨代码，也一直想干掉他。仔细分析一下，这坨代码不光不好看，而且每次调用都要创建这么多对象也是不必要的，我们可以引入复合享元进行重构。

首先对这段代码及其背后的实现做一个简单的说明。实际项目中我们可能需要对方法结果做一些后置的处理，假设我们有多个后置结果处理器，并且这些处理器具备优先级，同时可以随机进行组合。对于结果处理器而言，没有必要每次请求都创建一个新的对象，所以可以用享元模式进行改造，因为结果处理器是可以随意组合的，所以这里可以采用复合享元来根据外部状态构建合适的复合处理器。

前面有介绍说复合享元是由单纯享元复合而成的，所以我们可以先设计好单纯享元，然后由单纯享元复合成复合享元。区分外部状态和内部状态是享元模式的关键，对于本例子而言，处理器的优先级是内部状态，而待处理的结果则是外部状态，单纯享元的实现如下：

- 享元：抽象结果处理器

```java
public abstract class ResultHandler implements Comparable<ResultHandler> {

    /** 内部状态：基础优先级 */
    protected static final int DEFAULT_PRIORITY = 0;

    /**
     * 处理函数
     *
     * @param obj
     */
    public abstract void handle(Object obj);

    public abstract int priority();

    @Override
    public int compareTo(ResultHandler that) {
        return that.priority() - this.priority();
    }

}
```

- 具体享元：具体结果处理器

```java
public class FirstResultHandler extends ResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("first handle");
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY + 40;
    }

}

public class SecondResultHandler extends ResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("second handle");
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY + 30;
    }

}

public class ThirdResultHandler extends ResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("third handle");
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY + 20;
    }

}
```

- 享元工厂

```java
public class ResultHandlerFactory {

    private Map<String, ResultHandler> handlerMap = new ConcurrentHashMap<>(3);

    private static volatile ResultHandlerFactory instance;

    private ResultHandlerFactory() {
    }

    public static ResultHandlerFactory getInstance() {
        if (null == instance) {
            synchronized (ResultHandlerFactory.class) {
                if (null == instance) {
                    instance = new ResultHandlerFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 获取结构处理器享元
     *
     * @param key
     * @return
     */
    public ResultHandler getResultHandler(String key) {
        // 先从缓存中获取
        ResultHandler handler = handlerMap.get(key);
        if (null != handler) {
            return handler;
        }

        // 创建并缓存新的实例
        switch (key) {
            case "1": {
                handler = new FirstResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            case "2": {
                handler = new SecondResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            case "3": {
                handler = new ThirdResultHandler();
                handlerMap.put(key, handler);
                break;
            }
            default:
                throw new IllegalArgumentException("unknown handler key: " + key);
        }
        return handler;
    }

}
```

- 复合享元：复合结果处理器

```java
public class CompositeResultHandler extends ResultHandler {

    /** 存储构造复合享元的单纯享元集合 */
    private List<ResultHandler> handlers = new ArrayList<>();

    @Override
    public void handle(Object obj) {
        for (final ResultHandler handler : handlers) {
            handler.handle(obj);
        }
    }

    @Override
    public int priority() {
        return DEFAULT_PRIORITY;
    }

    public void add(ResultHandler handler) {
        handlers.add(handler);
        // 对处理按照优先级由大到小排序
        Collections.sort(handlers);
    }

}
```

- 复合享元工厂

```java
public class CompositeResultHandlerFactory {

    private ResultHandlerFactory factory = ResultHandlerFactory.getInstance();

    /** 基于静态内部类的单例实现 */
    private static class InnerClass {
        private static final CompositeResultHandlerFactory INSTANCE = new CompositeResultHandlerFactory();
    }

    private CompositeResultHandlerFactory() {
    }

    public static CompositeResultHandlerFactory getInstance() {
        return InnerClass.INSTANCE;
    }

    /**
     * 获取结果处理器复合享元
     *
     * @param key
     * @return
     */
    public CompositeResultHandler getCompositeResultHandler(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 构建复合享元
        CompositeResultHandler compositeHandler = new CompositeResultHandler();
        for (int i = 0; i < key.length(); i++) {
            ResultHandler handler = factory.getResultHandler(String.valueOf(key.charAt(i)));
            if (null != handler) {
                compositeHandler.add(handler);
            }
        }
        return compositeHandler;
    }

}
```

- 客户端

```java
CompositeResultHandlerFactory factory = CompositeResultHandlerFactory.getInstance();

ResultHandler handler = factory.getCompositeResultHandler("12");
handler.handle(new Object());

factory.getCompositeResultHandler("13");
handler.handle(new Object());
```
