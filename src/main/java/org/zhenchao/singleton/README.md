### 一. 单例模式
#### 1. 单例模式解决的问题
&emsp;&emsp;在面向对象程序设计中，只要内存允许，我们通常都可以为一个对象创建任意个实例，但是有些时候这不一定是一件好的事情，考虑一个文件类，在被使用之前需要从磁盘加载一定量的数据，我们肯定不希望每次调用该对象都去执行数据加载的操作，费时，而且同样的数据因为一个对象的实例化操作就要在内存中重复再存一份，显然是对内存的一种浪费，这个时候我就希望我对数据的加载操作只执行一次，后面所有的调用都是对这份数据的复用，这个时候我们就需要使用到单例，从而让这个对象在内存中仅有唯一的实例。  
&emsp;&emsp;如果我们可以任意的创建对象，那么如果我们希望内存中仅保有一份实例，就必须让所有的程序开发人员维持一个约定，只实例化该对象一次，然而现实是对象是可以任意实例化的，而约定程序开发人员是不可能的，这个时候我们就需要从开发人员手中剥夺对目标对象实例化的权利，而由程序单例模式去控制对象的创建，并暴露给开发人员一个获取对象实例的接口。  
&emsp;&emsp;单例模式广泛应用于我们的程序设计之中，比如缓存实例，窗口实例，计数器等等，是程序设计中几个最常用的设计模式之一。

#### 2. 三大类单例模式的java实现及其优缺点
- 饿汉式

```java
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * 私有构造函数
     */
    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

}
```

&emsp;&emsp;饿汉式的优点在于实现简单，无须考虑线程安全问题，但是因为在编译期就已经完成实例化对象，所以如果该对象一直不被使用，则是对内存的浪费，此外此种方式会增加编译期时长。

- 饿汉式（基于枚举）

```java
public enum EnumSingleton {

    INSTANCE;

    public void otherMethod() {

    }

}
```

&emsp;&emsp;相对于传统饿汉式，基于枚举的饿汉式具备饿汉式所有的优点，同时更加简单，此外基于枚举的饿汉式还有一个非常重要的特性，就是 __防止反序列化创建新的对象__，不过java1.5之后才有枚举类，所以之前的jdk没有这样的福利哦，不过线程的jdk版本基本上都是1.6之后，所以 __大力推荐__ 这类方式。

- 懒汉式

```java
public class LazySingleton {

    private static LazySingleton instance;

    /**
     * 私有的构造方法
     */
    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
```

&emsp;&emsp;懒汉式相对于饿汉式的最大优点在于 __按需实例化对象__，懒汉式没有在编译期就触发对象实例化，而是推迟到getInstance第一次被调用的时候，也正因为如此，所以我们需要考虑线程安全问题，常规的懒汉式直接简单粗暴的在getInstance前面加了一个`synchronized`修饰，这样虽然保证了线程安全，但也严重降低了性能，不太推荐这种方式。

- 懒汉式（双重检查）

```java
public class DoubleCheckSingleton {

    // 须使用volatile关键字修饰
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

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

&emsp;&emsp;为了提高懒汉式的性能，出现了 __双重检查机制__，这样就可以保证在对象实例化之后，对于获取对象的操作只需要执行一次`if`判断即可，不需要阻塞，从而极大提升性能。为了保证对象实例的线程可见性，所以对象实例需要用关键字`volatile`修饰，但是因为1.4以及更早版本的java中，许多JVM对于`volatile`关键字的检查会导致双重检查加锁失效，所以这种方式仅能够在jdk1.5版本之后使用。  
&emsp;&emsp;在此说明一下为什么需要用两次判`null`，考虑有线程1和线程2都经过第一个`if`，来到`synchronized`前面，假设此时1拿到了锁，进入了同步块，等1出了同步块之后，释放了锁，此时2拿到了锁，进入了同步块，如果此时不再次判`null`，则会再次实例化对象，从而达不到单例的目的。

- 登记式

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

    public ChildRegisterSingleton() {
    }

    public static ChildRegisterSingleton getInstance() {
        return (ChildRegisterSingleton) RegisterSingleton.getInstance("org.zhenchao.singleton.ChildRegisterSingleton");
    }

}
```

&emsp;&emsp;因为懒汉式和饿汉式的不可继承性，所以引出了登记式，常规的登记式通过在父类维持一个map，来记录子类的实例，从而保证每次子类调用`getInstance()`方法都能返回唯一的实例，不过事情没有说的那么美好，因为父类的构造函数是`protected`修饰，所以子类的构造函数也必须由`protected`及以上宽泛权限的修饰符修饰，这样就会导致我们可以通过`new`关键字实例化子类，而无需将该实例注册到父类。除此之外，父类在创建实例的过程也存在线程安全问题，所以 __无视这一方法吧__。

- 登记式（基于静态内部类）

```java
public class InnerClassSingleton {

    private static class InnerClass {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 私有构造方法
     */
    private InnerClassSingleton() {
    }

    public static InnerClassSingleton getInstance() {
        return InnerClass.INSTANCE;
    }
}
```

&emsp;&emsp;基于静态内部类的登记式方式可以达到 __懒加载__ 的目的，同时实现简单，又无需考虑多线程问题，所以比较推荐。

&emsp;&emsp;最后还是需要提醒一下， __所有的单例都是针对同一个类加载加载而言的__ ，如果是不同的类加载器，则无单例可言。

### 二. 多例模式
#### 1. 多例模式解决的问题
&emsp;&emsp;上面讲的单例模式是任何时候都返回同一个实例，但是有时候我们会需要一个类视具体情况返回多个实例的情况，不同于`new`的任意创建，这个时候返回的实例是由多例模式控制的，我们只有获取实例的权力，而没有创建实例的权力，我们把这种模式称为多例模式。

#### 2. 有上限多例和无上限多例
##### 2.1 有上限多例
&emsp;&emsp;有上限多例是指类的实例个数是可数的，比如我们有一个`Cache`类，我们希望针对本地和全局创建两个缓存实例，那么此时就可以利用多例模式创建具有两个上限实例的对象，具体实现如下：

```java
public class Cache {

    /** 本地缓存 */
    private static final Cache LOCAL_CACHE = new Cache();

    /** 全局缓存 */
    private static final Cache GLOBAL_CACHE = new Cache();

    private Cache() {
    }

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

##### 2.2 无上限多例
&emsp;&emsp;假设我们现在需要针对每一个用户都创建一个缓存实例，因为用户数是未知的，此时我们就需要采用无上限的多例模式，通过一个map集合来缓存用户的cache实例，代码实现如下：

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
