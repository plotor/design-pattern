&emsp;&emsp;原型模式也是用来创建对象，不同于其他对象创建模式采用`new`关键字创建对象，原型模式则采用clone的方式，从一个对象复制一份对象出来（根据业务需要可以对复制出来的对象进行适当的修改），所以原型模式一般适合创建比较复杂的对象，并且不需要关注对象的具体类型。

&emsp;&emsp;原型模式可以分为 __简单原型模式__ 和 __登记原型模式__，后者相对于前者仅仅是多了一个原型管理器，用于管理clone出来的对象，简单原型模式包含如下几种角色：

- __客户角色__：提出创建对象的需求
- __抽象原型__：抽象具体原型所需的接口
- __具体原型__：实现抽象原型声明的接口

而登记原型模式则在简单原型模式角色的基础上增加了一个 __原型管理器角色__。

### 一. 简单原型模式
&emsp;&emsp;说到克隆，我最先想到是克隆羊“多莉”，假设我们的克隆技术成熟，未来的某一天，我们可以克隆所有的小动物，比如说“克隆人”，如果用程序去模拟这种克隆操作应该如何实现？

#### 1.1 不使用模式的实现
如果不使用模式，我们可以实现如下：

- __动物抽象__

```java
/**
 * 动物抽象，标记接口
 * @author zhenchao.wang 2016-10-30 10:36
 * @version 1.0.0
 */
public interface Animal {
}
```

- __动物具象__：人、羊

```java
/**
 * 人
 *
 * @author zhenchao.wang 2016-10-30 10:46
 * @version 1.0.0
 */
public class Person implements Animal {

    /** 姓名 */
    private String name;

    /** 年龄 */
    private int age;

    /** 性别 */
    private int gender;

    /** 体重 */
    private int weight;

    public Person(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    public Person(int gender, int weight) {
        this.age = 1;
        this.gender = gender;
        this.weight = weight;
    }

    // 省略getter和setter
}

/**
 * 羊
 *
 * @author zhenchao.wang 2016-10-30 10:39
 * @version 1.0.0
 */
public class Sheep implements Animal {

    /** 性別 */
    private int gender;

    /** 体重 */
    private int weight;

    /** 毛色 */
    private String color;

    public Sheep(int gender, String color) {
        this.gender = gender;
        this.color = color;
    }

    public Sheep(int gender, int weight, String color) {
        this.gender = gender;
        this.weight = weight;
        this.color = color;
    }

    // 省略getter和setter
}
```

- __客户端__：复制机

```java
/**
 * 复制机
 *
 * @author zhenchao.wang 2016-10-30 10:50
 * @version 1.0.0
 */
public class CloneClient {

    /**
     * 克隆
     *
     * @param animal
     * @return
     */
    public Animal clone(Animal animal) {
        if (animal instanceof Person) {
            Person person = (Person) animal;
            Person clonePerson = new Person(person.getName(), person.getGender());
            clonePerson.setAge(1);
            clonePerson.setWeight(0);
            return clonePerson;
        } else if (animal instanceof Sheep) {
            Sheep sheep = (Sheep) animal;
            Sheep cloneSheep = new Sheep(sheep.getGender(), sheep.getColor());
            cloneSheep.setWeight(0);
            return cloneSheep;
        } else {
            throw new IllegalArgumentException("Unknown animal type!");
        }
    }

    public static void main(String[] args) {
        CloneClient client = new CloneClient();

        Person person = new Person(0, 6);
        person.setName("张三");
        System.out.println(person);
        System.out.println("clone:" + client.clone(person));

        Sheep sheep = new Sheep(1, 2, "white");
        System.out.println(sheep);
        System.out.println("clone:" + client.clone(sheep));
    }

}
```

输出如下：

```text
张三,1,0,6
clone:张三,1,0,0
1,2,white
clone:1,0,white
```

&emsp;&emsp;上面的实现主要的缺陷是针对具体实现编程，设想一下如果把这种实现扩展到所有的动物，那么`clone()`方法将会变得相当复杂，而且`CloneClient`必须去了解每个动物的具体细节，这样的实现不易于扩展。换个角度想想，克隆这一特性应该是动物自己的属性，从面向对象的角度来说，`clone()`这个方法应该是动物自己的方法，而不应该是由外部去主导如何克隆自己，所以这里我们可以用原型模式去对其进改造。

#### 1.2 基于原型模式的实现
采用原型模式，我们将克隆这一属性交给对象自身，具体实现如下：

- __抽象原型__：动物抽象

```java
/**
 * 动物抽象
 * @author zhenchao.wang 2016-10-30 10:36
 * @version 1.0.0
 */
public interface Animal {

    /**
     * 克隆
     * @return
     */
    Animal clone();

}
```

- __具体原型__：人、羊

```java
/**
 * 人
 *
 * @author zhenchao.wang 2016-10-30 10:46
 * @version 1.0.0
 */
public class Person implements Animal {

    /** 姓名 */
    private String name;

    /** 年龄 */
    private int age;

    /** 性别 */
    private int gender;

    /** 体重 */
    private int weight;

    public Person(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    public Person(int gender, int weight) {
        this.age = 1;
        this.gender = gender;
        this.weight = weight;
    }

    @Override
    public Animal clone() {
        Person person = new Person(this.getName(), this.getGender());
        person.setAge(1);
        person.setWeight(0);
        return person;
    }

    // 省略getter和setter
}

/**
 * 羊
 *
 * @author zhenchao.wang 2016-10-30 10:39
 * @version 1.0.0
 */
public class Sheep implements Animal {

    /** 性別 */
    private int gender;

    /** 体重 */
    private int weight;

    /** 毛色 */
    private String color;

    public Sheep(int gender, String color) {
        this.gender = gender;
        this.color = color;
    }

    public Sheep(int gender, int weight, String color) {
        this.gender = gender;
        this.weight = weight;
        this.color = color;
    }

    @Override
    public Animal clone() {
        Sheep sheep = new Sheep(this.getGender(), this.getColor());
        sheep.setWeight(0);
        return sheep;
    }

    // 省略getter和setter
}
```

- __客户端角色__

```java
/**
 * 复制机
 *
 * @author zhenchao.wang 2016-10-30 10:50
 * @version 1.0.0
 */
public class CloneClient {

    public static void main(String[] args) {
        CloneClient client = new CloneClient();

        Person person = new Person(0, 6);
        person.setName("张三");
        System.out.println(person);
        System.out.println("clone:" + person.clone());

        Sheep sheep = new Sheep(1, 2, "white");
        System.out.println(sheep);
        System.out.println("clone:" + sheep.clone());
    }

}
```

输出同上

&emsp;&emsp;采用原型模式之后，代码变得更加简洁，客户端只需要关注自己的业务，而不需要去思考对象具体的克隆细节，只需要调用对象的`clone()`方法即可。

### 二. 登记原型模式
&emsp;&emsp;登记时原型模式相对于简单原型模式多了一个原型管理器，客户端在创建克隆原型对象之前，可以先从管理器中查找是否有满足要求的对象，有的话直接获取接口，否则就克隆一份，并将得到的对象交给原型管理器去存储，时间开发中这种方式使用的较少。

一个原型管理器的简单实现：

```java
/**
 * 原型管理器
 *
 * @author zhenchao.wang 2016-10-30 12:25
 * @version 1.0.0
 */
public class PrototypeManager {

    private List<Animal> animals = new ArrayList<>();

    public void add(Animal animal) {
        animals.add(animal);
    }

    public Animal get(int i) {
        return animals.get(i);
    }

    public int getSize() {
        return animals.size();
    }

}
```

### 三. Java中浅拷贝与深拷贝

&emsp;&emsp;克隆是创建对象的方法之一，从直观上看是创建了一个与当前对象在内容上一模一样的对象，对于`clone()`，一般需要满足下面三个条件：

```text
1. 对任何对象x，都有x.clone() != x
2. 对任何对象x，都有x.clone().getClass() == x.getClass()
3. 如果equals()方法定义恰当，那么x.clone().equals(x) == true
```

&emsp;&emsp;java语言提供了`Cloneable`接口，这是一个标记接口，用于标记当前对象允许被克隆，而具体的克隆方法声明则在`Object`类中：

```java
protected native Object clone() throws CloneNotSupportedException;
```

&emsp;&emsp;所以可以看出java语言对于原型模式提供了原生支持，借助于java的`Cloneable`接口，我们可以省略抽象原型，直接在具体原型中实现克隆的逻辑，不过使用这种方式的前提需要了解一下java中的浅拷贝和深拷贝。

- __浅拷贝__

&emsp;&emsp;通常我们拷贝对象都是直接将一个对象的引用直接赋值给一个变量，这样两个变量都持有同一个对象的引用，所以只要这个对象一发生变化，马上就会反应到这两个变量上。比如：
```java
School school = new School("CIT");
School s1 = school;
School s2 = school;
```
&emsp;&emsp;这种情况下，`s1`和`s2`都是持有对象`school`的引用，一旦`school`发生变化，`s1`和`s2`也会跟着变，这时如果不希望两个变量之前相互影响，我们就需要对变量进行真正的拷贝处理。

&emsp;&emsp;在java中，如果某个类希望具有拷贝的能力，需要实现`Cloneable`接口（这只是一个标记接口），同时覆盖`Object`类中的`clone()`方法，比如：

```java
public class School implements Cloneable {

    /** 名称 */
    private String name;

    /** 校龄 */
    private int age;

    /** 校长 */
    private President president;
    
    public School(String name, int age, President president) {
        this.name = name;
        this.age = age;
        this.president = president;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //省略getter和setter
}
```

&emsp;&emsp;这样`School`类就具有了自我拷贝的能力，当我们执行下面的代码了之后，`school_copy`就变成`了school`的一个副本:

```java
School school = new School("CIT", 60, president)；
School school_copy = school.clone();
```

&emsp;&emsp;如果我们使用的是默认的`clone()`方法，得到的就是一个对象的浅拷贝，这里的拷贝只会拷贝真正属于对象的变量，在这里`name`和`age`会被真正拷贝一份，而`president`是引用类型，所以只会拷贝一份引用，本质上还是指向同一个对象，如果我们这时将`president`的名字改了，那么会反应到`school`和`school_copy`上，但是如果我们只是改变了其中一个对象的`age`，那么另一个对象的`age`是不会发生变化的，示例如下：

```java
School school = new School("WHU", 120, new President("李晓红"));
School school_copy = (School) school.clone();  // 浅拷贝

System.out.println(school.getPresident().hashCode() + "\t" + school_copy.getPresident().hashCode());
school.getPresident().setName("李达");

System.out.println("origin:" + school);
System.out.println("copy:" + school_copy);
```

输出如下：

```java
258952499	258952499
origin:HUST,120,李达
copy:WHU,120,李达
```

- __深拷贝__

&emsp;&emsp;如果我们希望真正的得到`school`对象的拷贝，即域`name, age, president`都是专属的，那么就需要修改默认的`clone`方法来达到目的。具体的思路就是在`school`对象的`clone`方法里面再调用`president`的`clone`方法，代码如下：

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    School school = (School) super.clone();
    school.president = (President) this.president.clone();
    return school;
}
```

其他代码不变，输出如下：

```text
258952499	603742814
origin:WHU,120,李达
copy:WHU,120,李晓红
```

&emsp;&emsp;从上可以看出，通过这种方式深拷贝后，就得到了真正意义上的两份`school`对象。除了上面这种方式，我们还可以 __基于java的序列化和反序列化机制实现深拷贝__，具体实现如下，不过需要注意的是Java的序列化还是比较耗时的，所以一般不得已不推荐这种方式。

```java
// 基于java的序列化和反序列化机制实现深拷贝
protected Object clone() throws CloneNotSupportedException {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return ois.readObject();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (null != ois) {
                ois.close();
            }
            if (null != oos) {
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    return null;
}
```