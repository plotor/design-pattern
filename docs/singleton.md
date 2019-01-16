### 一. 单例模式

#### 1.1 单例模式解决的问题

在面向对象程序设计中，只要内存允许我们通常都可以为一个对象创建任意个实例，但是一些场景下这不一定是一件好的事情。考虑一个文件类，在被使用之前需要从磁盘加载一定量的数据，我们肯定不希望每次调用该对象都去执行数据加载的操作，费时，而且同样的数据因为一个对象的实例化操作就要在内存中重复再存一份，显然是对内存的一种浪费，这个时候我们就希望对数据的加载操作只执行一次，后面所有的调用都是对这份数据的复用。这个时候我们就需要使用到单例模式，从而让这个对象在内存中仅有唯一的实例。<!-- more -->

如果我们可以任意的创建对象，那么如果我们希望内存中仅保有一份实例，就必须让所有的程序开发人员维持一个约定，只实例化该对象一次，然而现实是对象是可以任意被实例化的，约定开发人员是不现实的，这个时候我们就需要从开发人员手中剥夺对目标对象实例化的权利，而由单例模式去控制对象的创建，并暴露给开发人员一个获取对象实例的入口。

单例模式广泛应用于我们的程序设计之中，比如缓存实例，窗口实例，计数器等等，是程序设计中最常用的设计模式之一。

#### 1.2 三大类单例模式的实现及其优缺点

##### 1.2.1 饿汉式

```java
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造函数
     */
    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

}
```

饿汉式的优点在于实现简单，无须考虑线程安全问题，但是因为在编译期就已经完成实例化对象，如果该对象一直不被使用则是对内存的浪费，此外此种方式会增加编译期时长。

##### 1.2.2 饿汉式：基于枚举

```java
public enum EnumSingleton {

    INSTANCE;

    public void otherMethod() {

    }

}
```

相对于传统饿汉式，基于枚举的饿汉式具备饿汉式所有的优点，同时更加简单。此外，基于枚举的饿汉式还有一个非常重要的特性，就是 __防止反序列化创建新的对象__，测试如下：

```java
public class EnumSingletonTest implements Serializable {

    private static final long serialVersionUID = 991713955127096061L;

    @Test
    public void test() throws Exception {
        // 枚举类型反序列化得到的还是原对象
        EnumSingleton newInstance = this.deserialize(this.serialize(EnumSingleton.INSTANCE));
        Assert.assertEquals(EnumSingleton.INSTANCE, newInstance);

        // 普通类型反序列化会创建新的对象
        EnumSingletonTest newInstance2 = this.deserialize(this.serialize(this));
        Assert.assertNotEquals(this, newInstance2);

    }

    /**
     * 序列化
     *
     * @param obj
     * @return
     * @throws IOException
     */
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        try {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } finally {
            oos.close();
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T deserialize(byte[] bytes) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        try {
            return (T) ois.readObject();
        } finally {
            ois.close();
        }
    }
}
```

枚举类型之所以在反序列化时不会创建新的对象是因为 java 对于枚举类型的序列化操作进行了特殊处理。相对于普通对象，java 在序列化枚举对象时实际上只是调用 ``Enum#name`` 方法获取当前对象的 name 值，并将 name 值进行序列化存储，当执行反序列化时实际上是拿到 name 值，然后通过调用 `Enum#valueOf` 方法获取 name 值对应的枚举对象。此外，为了防止破坏该机制，所有自定义的 writeObject、readObject、readObjectNoData、writeReplace，以及 readResolve 方法在执行序列化操作时都会被忽略。官方文档如下：

> __Serialization of Enum Constants__
>
> Enum constants are serialized differently than ordinary serializable or externalizable objects. The serialized form of an enum constant consists solely of its name; field values of the constant are not present in the form. To serialize an enum constant, ObjectOutputStream writes the value returned by the enum constant's name method. To deserialize an enum constant, ObjectInputStream reads the constant name from the stream; the deserialized constant is then obtained by calling the java.lang.Enum.valueOf method, passing the constant's enum type along with the received constant name as arguments. Like other serializable or externalizable objects, enum constants can function as the targets of back references appearing subsequently in the serialization stream.
>
> The process by which enum constants are serialized cannot be customized: any class-specific writeObject, readObject, readObjectNoData, writeReplace, and readResolve methods defined by enum types are ignored during serialization and deserialization. Similarly, any serialPersistentFields or serialVersionUID field declarations are also ignored--all enum types have a fixed serialVersionUID of 0L. Documenting serializable fields and data for enum types is unnecessary, since there is no variation in the type of data sent.

不过 java 1.5 之后才有枚举类，所以之前的 jdk 没有这样的福利，不过现在的 jdk 版本基本上都是 1.6 之后，所以 __大力推荐__ 这类方式。

对于其它单例模式的实现方式，如果希望能够在反序列化时不创建新的对象，我们可以实现 readResolve 方法，并在该方法中返回单例对象，如下：

```java
public class EnumSingletonTest implements Serializable {

    private static final long serialVersionUID = 991713955127096061L;

    private static final EnumSingletonTest INSTANCE = new EnumSingletonTest();

    @Test
    public void test() throws Exception {
        // 反序列化操作没有创建新的对象
        EnumSingletonTest newInstance2 = this.deserialize(this.serialize(INSTANCE));
        Assert.assertEquals(INSTANCE, newInstance);

    }

    /**
     * 防止反序列化创建新的对象
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    // ... 省略序列化和反序列化方法
}
```

##### 1.2.3 懒汉式

```java
public class LazySingleton {

    private static LazySingleton instance;

    /**
     * 私有的构造方法
     */
    private LazySingleton() { }

    public synchronized static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
```

懒汉式相对于饿汉式的最大优点在于 __按需实例化对象__，懒汉式没有在编译期就触发对象实例化，而是推迟到 getInstance 方法第一次被调用的时候，也正因为如此我们需要考虑线程安全问题，常规的懒汉式直接简单粗暴的在 getInstance 方法前面加了一个 `synchronized` 关键字修饰，这样虽然保证了线程安全但也严重降低了性能，不太推荐这种方式。

##### 1.2.4 懒汉式：双重检查加锁

```java
public class DoubleCheckSingleton {

    // 须使用volatile关键字修饰
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() { }

    public DoubleCheckSingleton getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
```

为了提高普通懒汉式的性能，出现了 __双重检查加锁机制（DCL:Double-checked locking）__，这样就可以保证在对象实例化之后，对于获取对象的操作只需要执行一次 if 判断即可，不需要阻塞，从而极大提升性能。为了保证对象实例的线程可见性，所以对象实例需要使用关键字 volatile 修饰，但是因为 1.4 以及更早版本的 java 中，许多 JVM 实现对于 volatile 关键字的检查会导致双重检查加锁失效，所以这种方式仅能够在 jdk 1.5 版本之后使用。

__为什么需要两次判 null ?__

在此说明一下为什么需要用两次判 null，考虑有线程 1 和线程 2 都经过第一个 if 来到 `synchronized` 前面，假设此时 1 拿到了锁进入了同步块，等 1 出了同步块之后释放了锁，此时 2 拿到了锁进入了同步块，如果此时不再次判 null，则会再次实例化对象从而达不到单例的目的。

__为什么必须使用 volatile 关键字修饰？__

在具体分析之前我们需要知道 volatile 关键字具备的两大特性：1.保证线程可见性；2.禁止指令重排。考虑上面的示例，如果不加 volatile 修饰会怎么样呢，这里先给出结论：__如果不加 volatile 修饰，那么某个线程读取到的不为 null 的对象实例可能还未被初始化__。

一个对象的实例化过程可以简单的抽象为如下三个步骤：

> 1. 为对象分配内存空间
> 2. 初始化对象
> 3. 将内存空间地址赋值给对应的变量

JIT 在编译上述过程指令时依据优化策略可能会对上述步骤进行重排序，比如将 3 排序到 2 的前面，这个时候在并发环境下就存在问题。假设线程 A 进入了临界区执行对象的实例化操作，由于 3 排在了 2 的前面，所以在线程 A 执行完 3 之后 instance 变量已不为 null，但是此时还没有执行 2，所以 instance 还没有被初始化，是一个不完整的对象，假设此时线程 B 到达第一个 if 语句，因为此时 instance 不为 null 所以继续往后走，但是线程 B 并不知道当前的 instance 还没有被初始化，一旦使用该对象就会出现问题，而 volatile 禁止指令重排的内存语义可以避免上述情况的发生。

##### 1.2.5 登记式

```java
public class RegisterSingleton {

    private static Map<String, Object> registry = new HashMap<>();

    static {
        RegisterSingleton instance = new RegisterSingleton();
        registry.put(instance.getClass().getName(), instance);
    }

    protected RegisterSingleton() {
    }

    public static RegisterSingleton getInstance(String name) {
        if (null == name) {
            name = "org.zhenchao.singleton.RegisterSingleton";
        }
        if (null == registry.get(name)) {
            try {
                registry.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterSingleton) registry.get(name);
    }
}

public class ChildRegisterSingleton extends RegisterSingleton {

    public ChildRegisterSingleton() { }

    public static ChildRegisterSingleton getInstance() {
        return (ChildRegisterSingleton) RegisterSingleton.getInstance("org.zhenchao.singleton.ChildRegisterSingleton");
    }

}
```

因为懒汉式和饿汉式的不可继承性，所以引出了登记式，常规的登记式通过在父类维持一个 map 以记录子类的实例，从而保证每次子类调用 getInstance 方法都能返回唯一的实例，不过事情没有想象的那么美好，因为父类的构造函数是 `protected` 修饰的，所以子类的构造函数也必须由 `protected` 及以上宽泛权限的修饰符修饰，这样就会导致我们可以通过 `new` 关键字实例化子类，而无需将该实例注册到父类。除此之外，父类在创建实例的过程也存在线程安全问题，所以 __无视这一方法吧__。

##### 1.2.6 登记式：基于静态内部类

```java
public class InnerClassSingleton {

    private static class InnerClass {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    /**
     * 私有构造方法
     */
    private InnerClassSingleton() { }

    public static InnerClassSingleton getInstance() {
        return InnerClass.instance;
    }
}
```

基于静态内部类的登记式方式可以达到 __懒加载__ 的目的，同时实现简单，又无需考虑多线程问题，所以比较推荐。__该方式依赖于 JVM 在初始化类时会进行加锁处理，java 语言规范规定对于每一个类或接口都有一个唯一的初始化锁与之对应，这一部分的具体执行逻辑由 JVM 实现，从而保证一个类在运行期间仅被初始化一次__。

最后还是需要提醒一下， __所有的单例都是针对同一个 ClassLoader 加载而言的__ ，如果是不同的 ClassLoader 则无单例可言。

### 二. 多例模式

#### 2.1 多例模式解决的问题

上面讲的单例模式是任何时候都返回同一个实例，但是有时候我们会需要一个类视具体情况返回多个实例的情况，不同于 `new` 的任意创建，这个时候返回的实例是由多例模式控制的，我们只有获取实例的权力而没有创建实例的权力，我们把这种模式称为多例模式。

#### 2.2 有上限多例和无上限多例

##### 2.2.1 有上限多例

有上限多例是指类的实例个数是可数的，比如我们有一个 Cache 类，我们希望针对本地和全局创建两个缓存实例，那么此时就可以利用多例模式创建具有两个上限实例的对象，具体实现如下：

```java
public class Cache {

    /** 本地缓存 */
    private static final Cache LOCAL_CACHE = new Cache();

    /** 全局缓存 */
    private static final Cache GLOBAL_CACHE = new Cache();

    private Cache() { }

    public static Cache getInstance(CacheType cacheType) {
        return CacheType.GLOBAL.equals(cacheType) ? GLOBAL_CACHE : LOCAL_CACHE;
    }

    /**
     * 缓存类型
     */
    public enum CacheType {
        LOCAL(1),
        GLOBAL(2);
        private int value;

        CacheType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
```

##### 2.2.2 无上限多例

假设我们现在需要针对每一个用户都创建一个缓存实例，因为用户数是未知的，此时我们就需要采用无上限的多例模式，通过一个 map 集合来缓存用户的 cache 实例，实现如下：

```java
public class LocalCache {

    private static Map<Long, LocalCache> instances = new HashMap<>();

    private LocalCache() {
    }

    public static LocalCache getInstance(long userId) {
        LocalCache cache = instances.get(userId);
        if (null == cache) {
            synchronized (LocalCache.class) {
                if (null == cache) {
                    cache = new LocalCache();
                    instances.put(userId, cache);
                }
            }
        }
        return cache;
    }

}
```
