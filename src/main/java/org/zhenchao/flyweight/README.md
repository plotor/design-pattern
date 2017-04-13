最近在看之前一个自己写的项目代码的时候，发现之前构造的责任链像个楼梯台阶一样的堆在那里，很是影响代码的美观性，并且一条链上的七、八个对象在每次请求时都需要创建一遍，对于一个高并发的项目来说，是一笔不小的开销，于是想对这一块的代码进行优化，而享元模式刚好满足我的需求。

享元模式（Flyweight）是 __以共享的方式有效地支持大量的细粒度对象__。能做到共享的关键是区分 __内部状态（Internal State）__ 和 __外部状态（External State）__。

__内部状态__：存储在享元对象内部，且不会随环境改变而改变的状态。

__外部状态__：随环境改变而改变，不可以共享的状态，必须由客户端保存，并在享元对象被创建之后，在需要使用的时候以参数形式传入到享元对象内部。

__外部状态不可以影响享元对象的内部状态，即外部状态和内部状态是独立的__。

享元模式就是将内部状态分离出来共享，称之为享元，通过共享享元对象来减少内存的占用，并将外部状态分离出来，放到外部让客户端去维护，并在需要的时候传递给享元对象使用。

享元模式分为 __单纯享元模式__ 和 __复合享元模式__。

### 单纯享元模式

单纯享元模式所定义的角色如下：

- 抽象享元

此角色是所有具体享元的超类，规定了具体享元需要实现的公共接口。

```java
/**
 * 抽象享元
 *
 * @author zhenchao.wang 2017-04-09 17:48
 * @version 1.0.0
 */
public interface AbstractFlyweight {

    /**
     * 操作函数
     *
     * @param externalState 外部状态
     */
    void operate(Object externalState);

}
```

- 具体享元

实现了抽象享元的具体的类，如果有内部状态的话，必须为内部状态提供存储空间。

```java
/**
 * 具体享元
 *
 * @author zhenchao.wang 2017-04-09 17:50
 * @version 1.0.0
 */
public class ConcreteFlyweight implements AbstractFlyweight {

    /** 内部状态 */
    private Object internalState;

    public ConcreteFlyweight(Object internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operate(Object externalState) {

    }

}
```

- 享元工厂

负责创建和管理享元角色，需要保证享元角色被适当的共享，当客户端需要一个享元对象的时候，工厂需要检查是否有已定义的合适的对象可供使用，如果没有则创建新的享元对象。

```java
/**
 * 享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-09 17:53
 * @version 1.0.0
 */
public class FlyweightFactory {

    private Map<String, AbstractFlyweight> map = new ConcurrentHashMap<>();

    private static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取享元
     *
     * @param key
     * @return
     */
    public AbstractFlyweight getFlyweight(String key) {
        AbstractFlyweight flyweight = map.get(key);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight("some internal state");
            map.put(key, flyweight);
        }
        return flyweight;
    }
}
```

- 客户端

维护一个对所有享元对象的引用，自行存储所有享元对象的外部状态。客户端不可以直接创建享元对象实例，而必须从工厂中获取。

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-09 18:16
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();
        AbstractFlyweight flyweight = factory.getFlyweight("any key");
        flyweight.operate("any external state");
    }

}
```

介绍完了单纯享元定义的各个角色，我以一个自己的实际项目来举例说明。游客账号是我们常见的一种账号类型，考虑到一般用户名密码的登录方式门槛较高，很多用户不愿意去登录，所以对于一些安全性要求不高的业务来说，我们可以为用户生成游客账号。比如在我的项目里，我选择了一些元素包括设备IMEI信息、安全芯片ID等信息生成游客账号，如果实在没有可以参考的因素，则直接生成随机的游客账号。在代码层面，针对每一种类型的账号，我都为其设计了相应的处理器，并且每个处理器都可以设置降级委托处理器，当当前处理器处理失败时，可以采用降级处理器处理。如果不用享元模式，那么每次来一个请求，我都需要根据具体的类型创建相应的处理器对象，然后处理请求，考虑到游客账号的请求量一般较大，所以有必要尽量避免创建一些不必要的对象。

我们先来分析一下这里面哪些是内部状态，哪些是外部状态，因为享元模式的核心就是要将内部状态和外部状态区分开来，将内部状态封装在享元内部实现共享，而外部状态则有客户端传递进来。很显然，游客账号生成的参考元素是外部状态，因为每一个设备都有不同的设备IMEI信息，有的设备甚至还有安全芯片，这些东西必须由外面传递进来，而一个处理器的降级处理器则是内部状态，因为降级处理器一旦设定，不会动态变化。具体代码的实现如下：

- 游客类型定义

```java
/**
 * 游客类型
 *
 * @author zhenchao.wang 2017-04-09 18:31
 * @version 1.0.0
 */
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
}
```

- 游客处理器抽象享元

```java
/**
 * 游客类型处理器抽象享元
 *
 * @author zhenchao.wang 2017-04-09 18:19
 * @version 1.0.0
 */
public abstract class AbstractVisitorTypeFlyweight {

    /**
     * 创建或恢复游客信息
     *
     * @param id
     * @return
     */
    public abstract void createOrRecoverVisitor(String id);

    /**
     * 内部创建或恢复游客信息
     *
     * @return
     */
    protected boolean createOrRecoverVisitorInternal() {
        // 模拟操作是否成功
        return RandomUtils.nextInt(0, 10) > 5;
    }

}
```

- 游客处理器具体享元

```java
/**
 * 默认游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:24
 * @version 1.0.0
 */
public class DefaultVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 内部状态：委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public DefaultVisitorTypeFlyweight() {
    }

    public DefaultVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String id) {
        System.out.println("Create visitor info in default schema!");
    }

}

/**
 * fid类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:26
 * @version 1.0.0
 */
public class FidVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 内部状态：委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public FidVisitorTypeFlyweight() {
    }

    public FidVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String fid) {
        System.out.println("Create or recover visitor info by fid : " + fid);

        if (null != delegateFlyweight && !this.createOrRecoverVisitorInternal()) {
            // 调用委托处理器处理
            delegateFlyweight.createOrRecoverVisitor(fid);
        }
    }

}

/**
 * DeviceId类型游客处理器享元
 *
 * @author zhenchao.wang 2017-04-09 18:28
 * @version 1.0.0
 */
public class DeviceIdVisitorTypeFlyweight extends AbstractVisitorTypeFlyweight {

    /** 内部状态：委托处理器 */
    private AbstractVisitorTypeFlyweight delegateFlyweight;

    public DeviceIdVisitorTypeFlyweight() {
    }

    public DeviceIdVisitorTypeFlyweight(AbstractVisitorTypeFlyweight delegateFlyweight) {
        this.delegateFlyweight = delegateFlyweight;
    }

    @Override
    public void createOrRecoverVisitor(String deviceId) {
        System.out.println("Create or recover visitor info by device id : " + deviceId);
    }

}
```

- 游客处理器享元工厂

```java
/**
 * 游客类型处理器享元工厂
 *
 * @author zhenchao.wang 2017-04-09 18:32
 * @version 1.0.0
 */
public class VisitorTypeFlyweightFactory {

    private static final VisitorTypeFlyweightFactory INSTANCE = new VisitorTypeFlyweightFactory();

    private Map<VisitorType, AbstractVisitorTypeFlyweight> flyweightMap = new ConcurrentHashMap<>();

    private VisitorTypeFlyweightFactory() {
    }

    public static VisitorTypeFlyweightFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 获取对应的享元
     *
     * @param visitorType
     * @return
     */
    public AbstractVisitorTypeFlyweight getFlyweight(VisitorType visitorType) {
        if (null == visitorType) {
            return null;
        }

        // 先从缓存中找
        AbstractVisitorTypeFlyweight flyweight = flyweightMap.get(visitorType);
        if (null != flyweight) {
            return flyweight;
        }

        switch (visitorType) {
            case DEFAULT: {
                flyweight = new DefaultVisitorTypeFlyweight();
                flyweightMap.put(VisitorType.DEFAULT, flyweight);
                break;
            }
            case FID: {
                // 设置降级委托处理器
                flyweight = new FidVisitorTypeFlyweight(new DefaultVisitorTypeFlyweight());
                flyweightMap.put(VisitorType.FID, flyweight);
                break;
            }
            case DEVICE: {
                flyweight = new DeviceIdVisitorTypeFlyweight();
                flyweightMap.put(VisitorType.DEVICE, flyweight);
                break;
            }
            default:
                break;
        }
        return flyweight;
    }
}
```

- 客户端

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-09 22:04
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        VisitorTypeFlyweightFactory factory = VisitorTypeFlyweightFactory.getInstance();

        AbstractVisitorTypeFlyweight flyweight = factory.getFlyweight(VisitorType.DEFAULT);
        flyweight.createOrRecoverVisitor(randomStr());

        flyweight = factory.getFlyweight(VisitorType.FID);
        flyweight.createOrRecoverVisitor(randomStr());

        flyweight = factory.getFlyweight(VisitorType.DEVICE);
        flyweight.createOrRecoverVisitor(randomStr());
    }

    private static String randomStr() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

}
```

通过上面的代码实现，可以发现享元工厂是整个享元模式实现共享的核心实现所在，通过对享元对象的缓存来保证每一个享元都只有一份，不过在实现之前要区分好内部状态和外部状态，不然会存在线程安全问题。

### 复合享元模式

复合享元由单纯享元复合而成，一般来说复合享元的组成元素是随外部状态而变化的，所以复合享元通常被描述为不可共享的，这里的不可共享是指这个复合享元不可共享，但是组成复合享元的单纯享元还是共享的，但是我觉得如果外部状态的种类是有限的，我们也可以将复合享元设计成可共享的。

复合享元定义了 5 种角色：抽象享元、具体享元、复合享元、享元工厂，客户端。其中除了复合享元，其余角色定义与单纯享元角色定义相同。__复合享元__ 所代表的对象是不可以共享的（所以也称作不可共享的享元对象），一个复合享元对象可以分解成多个单纯享元对象，复合享元具有以下两个责任：

1. 由单纯的享元对象复合而成，因此需要提供 `add()` 这样的聚集管理方法，一个复合对象具有不同的聚集，并且可以在对象创建之后被修改，所以复合对象的状态是可变的，也就不可共享的。

2. 复合享元对象实现了抽象享元接口。

我还是以一个自己在项目中遇到的实际例子来举例说明，话说有一天我在看自己以前写的项目代码的时候，看到了下面这样的一坨代码：

```java
// 构建处理器链
AbstractResultHandler resultHandler =
        new VisitorPassTokenCreateHandler(
                new SecurityHintUpdateHandler(
                        new AuthorizationModeHandler(
                                new SuccessResultHandler(), visitorInfo.getVisitorId(), requestParams.getSid()),
                        visitorInfo.getVisitorId(), requestParams.getSid()), visitorInfo);
```

实际上这一坨代码虽然看起来不好看，但是其中的设计还是还是很灵活的，这里面我采用了责任链模式，并允许随机组合各个 handler，但是在构造上采用了静态构造的方式，所以就有了这样一坨构造的过程，也一直想干掉他。仔细分析一下，这坨代码不光不好看，其中每次创都要创建这么多对象也是不必要的，我们来用复合享元进行重构。

首先对这段代码及其背后的实现做一个抽象的描述，实际项目中我们可能需要对方法结果做一些后置的处理，假设我们有1, 2, 3, 4四个后置结果处理器，并且这些处理器具备优先级，同时可以随机进行组合，对于结果处理器而言，没有必要每次请求都现场创建，所以可以用享元模式进行改造，因为结果处理器是可以随意组合的，所以这里可以采用复合享元来根据外部状态构建合适的复合处理器。

前面有介绍说复合享元是由单纯享元复合而成的，所以我们可以先设计好单纯享元，然后由单纯享元复合成复合享元。区分外部状态和内部状态是享元的关键，对于本例子而言，处理器的优先级是内部状态，而待处理的结果则是外部状态，单纯享元的实现如下：

- 结果处理器抽象享元

```java
/**
 * 抽象结果处理器
 *
 * @author zhenchao.wang 2017-04-12 23:42
 * @version 1.0.0
 */
public abstract class AbstractResultHandler implements Comparable<AbstractResultHandler> {

    /** 内部状态：基础优先级 */
    protected static final int BASE_PRIORITY = 0;

    /**
     * 处理函数
     *
     * @param obj
     */
    public abstract void handle(Object obj);

    /**
     * 返回处理器的优先级
     *
     * @return
     */
    public abstract int getPriority();

    @Override
    public int compareTo(AbstractResultHandler handler) {
        return handler.getPriority() - this.getPriority();
    }
}
```

- 结果处理器具体享元

```java
/**
 * first result handler implementation
 *
 * @author zhenchao.wang 2017-04-12 23:47
 * @version 1.0.0
 */
public class FirstResultHandler extends AbstractResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("first handle");
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY + 40;
    }

}

/**
 * second result handler implementation
 *
 * @author zhenchao.wang 2017-4-12 23:48:56
 * @version 1.0.0
 */
public class SecondResultHandler extends AbstractResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("second handle");
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY + 30;
    }

}

/**
 * third result handler implementation
 *
 * @author zhenchao.wang 2017-4-12 23:49:25
 * @version 1.0.0
 */
public class ThirdResultHandler extends AbstractResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("third handle");
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY + 20;
    }

}

/**
 * fourth result handler implementation
 *
 * @author zhenchao.wang 2017-4-12 23:50:11
 * @version 1.0.0
 */
public class FourthResultHandler extends AbstractResultHandler {

    @Override
    public void handle(Object obj) {
        System.out.println("fourth handle");
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY + 10;
    }

}
```

- 享元工厂

```java
/**
 * 单纯的结果处理器享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-13 22:09
 * @version 1.0.0
 */
public class ResultHandlerFactory {

    private Map<String, AbstractResultHandler> handlerMap = new ConcurrentHashMap<>(4);

    private static volatile ResultHandlerFactory instance;

    private ResultHandlerFactory() {
    }

    /**
     * 双检查单例
     *
     * @return
     */
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
    public AbstractResultHandler getResultHandler(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        AbstractResultHandler handler = handlerMap.get(key);
        if (null != handler) {
            return handler;
        }

        switch (key) {
            case "1": {
                handler = new FirstResultHandler();
                handlerMap.put("1", handler);
                break;
            }
            case "2": {
                handler = new SecondResultHandler();
                handlerMap.put("2", handler);
                break;
            }
            case "3": {
                handler = new ThirdResultHandler();
                handlerMap.put("3", handler);
                break;
            }
            case "4": {
                handler = new FourthResultHandler();
                handlerMap.put("4", handler);
                break;
            }
            default:
                // nothing
        }
        return handler;
    }
}
```

构造好了单纯的享元，我们再来复合实现复合享元：

- 结果处理器复合享元

```java
/**
 * 结果处理器复合享元
 *
 * @author zhenchao.wang 2017-04-13 21:42
 * @version 1.0.0
 */
public class CompositeResultHandler extends AbstractResultHandler {

    /** 存储构造复合享元的单纯享元集合 */
    private List<AbstractResultHandler> handlers = new ArrayList<>();

    @Override
    public void handle(Object obj) {
        for (final AbstractResultHandler handler : handlers) {
            handler.handle(obj);
        }
    }

    @Override
    public int getPriority() {
        return BASE_PRIORITY;
    }

    public void add(AbstractResultHandler handler) {
        handlers.add(handler);
        // 对处理按照优先级由大到小排序
        Collections.sort(handlers);
    }

}
```

- 复合享元工厂

```java
/**
 * 复合享元工厂（单例）
 *
 * @author zhenchao.wang 2017-04-12 23:52
 * @version 1.0.0
 */
public class CompositeResultHandlerFactory {

    private ResultHandlerFactory resultHandlerFactory = ResultHandlerFactory.getInstance();

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
            AbstractResultHandler handler = resultHandlerFactory.getResultHandler(String.valueOf(key.charAt(i)));
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
/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-13 22:45
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        CompositeResultHandlerFactory factory = CompositeResultHandlerFactory.getInstance();
        AbstractResultHandler handler = factory.getCompositeResultHandler("124");
        handler.handle(new Object());
    }

}
```

### 享元模式的应用场景

满足下列所有条件时，可以考虑使用享元模式：

> 1. 一个系统有大量的对象
> 2. 这些对象消耗着大量的内存
> 3. 这些对象中的大部分都可以外部化
> 4. 这些对象可以按照内部状态分成很多的组，当把外部对象从对象中剔除时，每一个组都可以仅用一个对象表示
> 5. 软件对象不依赖于这些对象的身份，即这些对象是不可分辨的

### 享元模式的优缺点

- 优点

大幅度降低了内存中对象的数量

- 缺点

系统设计更加复杂，将享元独享的状态外部化，拉长运行时间