### 原型模式

- [简单原型模式](#简单原型模式)
    - [角色](#角色)
    - [示例](#示例)
        - [不使用模式的实现](#不使用模式的实现)
        - [基于简单原型模式的实现](#基于简单原型模式的实现)
- [登记原型模式](#登记原型模式)
    - [角色](#角色)
    - [示例](#示例)
- [浅拷贝 & 深拷贝](#浅拷贝-深拷贝)
    - [浅拷贝](#浅拷贝)
    - [深拷贝](#深拷贝)

---

原型（prototype）模式同样是用来创建对象，不同于其他对象创建模式采用 new 关键字创建对象，原型模式采用 clone 的方式，从一个对象复制一份对象出来（根据业务需要可以对复制出来的对象进行适当的修改），所以原型模式一般适合创建比较复杂的对象，并且不需要关注对象的具体类型。

原型模式可以分为 __简单原型模式__ 和 __登记原型模式__，后者相对于前者仅仅是多了一个原型管理器，用于管理 clone 出来的对象。

#### 简单原型模式

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象原型 | Prototype | 抽象类 / 接口 | 定义用于克隆现有类对象生成新对象的方法
具体原型 | ConcretePrototype  | 继承或实现 Prototype | 实现克隆现有类对象生成新对象的方法
客户端 | Client | 类 | 调用原型类的克隆方法创建新的对象

##### 示例

说到克隆，我最先想到是克隆羊“多莉”，假设我们的克隆技术成熟，未来的某一天，我们可以克隆所有的小动物，比如说“克隆人”，如果用程序去模拟这种克隆操作应该如何实现？

###### 不使用模式的实现

如果不使用原型模式，我们可以实现如下：

- 动物抽象

```java
public interface Animal { }
```

- 动物具象

```java
public class Person implements Animal {

    private String name;
    private int age;
    private int gender;
    private int weight;

    public Person(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    // ... getter & setter
}

public class Sheep implements Animal {

    private int gender;
    private int weight;
    private String color;

    public Sheep(int gender, String color) {
        this.gender = gender;
        this.color = color;
    }

    // ... getter & setter
}
```

- 客户端

```java
public class CloneClient {

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
            throw new IllegalArgumentException("unknown animal type: " + animal);
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

上面的实现主要的缺陷是针对具体实现编程，设想一下如果把这种实现扩展到所有的动物，那么 `CloneClient#clone` 方法将会变得相当复杂，而且 CloneClient 必须去了解每个动物的实现细节。换个角度想想，克隆这一特性应该是动物自己的属性，从面向对象的角度来说，clone 这个方法应该是动物自己的方法，而不应该是由外部去指导如何克隆自己，所以这里我们可以用原型模式去对其进行改造。

###### 基于简单原型模式的实现

采用简单原型模式，我们将克隆这一属性归还给对象自身，具体实现如下：

- 抽象原型

```java
public interface Animal {

    Animal clone();

}
```

- 具体原型

```java
public class Person implements Animal {

    private String name;
    private int age;
    private int gender;
    private int weight;

    public Person(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public Animal clone() {
        Person person = new Person(this.getName(), this.getGender());
        person.setAge(1);
        person.setWeight(0);
        return person;
    }

    // ... getter & setter
}

public class Sheep implements Animal {

    private int gender;
    private int weight;
    private String color;

    public Sheep(int gender, String color) {
        this.gender = gender;
        this.color = color;
    }

    @Override
    public Animal clone() {
        Sheep sheep = new Sheep(this.getGender(), this.getColor());
        sheep.setWeight(0);
        return sheep;
    }

    // ... getter & setter
}
```

- 客户端角色

```java
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

采用原型模式之后，代码变得更加简洁，客户端只需要关注自己的业务，而不需要去思考对象具体的克隆细节，只需要调用对象的 `Prototype#clone` 方法即可。

#### 登记原型模式

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象原型 | Prototype | 抽象类 / 接口 | 定义用于克隆现有类对象生成新对象的方法
具体原型 | ConcretePrototype  | 继承或实现 Prototype | 实现克隆现有类对象生成新对象的方法
客户端 | Client | 类 | 调用原型类的克隆方法创建新的对象
原型管理器 | PrototypeManager | 类 | 管理原型对象集合

可以看到，登记原型模式在简单原型模式角色的基础上仅增加了一个原型管理器角色。

##### 示例

登记原型模式相对于简单原型模式多了一个原型管理器，客户端在创建克隆原型对象之前，可以先从管理器中查找是否有满足要求的对象，有的话直接就直接获取该对象，否则就克隆一份，并交给原型管理器去管理，实际开发中这种方式使用的较少。针对前面示例，对应的原型管理器实现如下：

```java
public class PrototypeManager {

    private Map<String, Animal> animals = new HashMap<>();

    public void add(String name, Animal animal) {
        animals.put(name, animal);
    }

    public Animal get(String name) {
        return animals.get(name);
    }

    public int getSize() {
        return animals.size();
    }

}
```

#### 浅拷贝 & 深拷贝

克隆是创建对象的方法之一，从直观上看是创建了一个与当前对象在内容上一模一样的对象。对于 clone 方法而言一般需要满足下面 3 个条件：

1. 对任何对象 x，都有 `x.clone() != x`。
2. 对任何对象 x，都有 `x.clone().getClass() == x.getClass()`。
3. 如果 equals 方法定义恰当，那么 `x.clone().equals(x) == true`。

Java 语言提供了 Cloneable 接口，这是一个标记接口，用于标记当前对象允许被克隆，而具体的克隆方法声明则在 Object 类中：

```java
protected native Object clone() throws CloneNotSupportedException;
```

所以可以看出 java 语言对于原型模式提供了内建的支持，借助于 Cloneable 接口，我们可以省略抽象原型，直接在具体原型中实现克隆的逻辑，不过使用这种方式的前提需要了解一下 java 中的浅拷贝和深拷贝。

##### 浅拷贝

通常我们拷贝对象都是直接将一个对象的引用直接赋值给一个变量，这样两个变量都持有同一个对象的引用，所以只要这个对象一发生变化，马上就会反应到这两个变量上。比如：

```java
School school = new School("CIT");
School s1 = school;
School s2 = school;
```

这种情况下 s1和 s2 都是指向 school 对象的引用，一旦 school 对象发生变化，引用 s1 和 s2 也会跟着变化。这时如果不希望两个变量之前相互影响，我们就需要对变量进行真正的拷贝处理。

在 java 中，如果某个类希望具有拷贝的能力，需要实现 Cloneable 标记接口，同时覆盖实现 `Object#clone` 方法，比如：

```java
public class School implements Cloneable {

    private String name;
    private int age;
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

这样 School 类就具有了自我拷贝的能力，当我们执行下面的代码了之后，对象 schoolCopy 就变成了 school 对象的一个副本:

```java
School school = new School("CIT", 60, president)；
School schoolCopy = school.clone();
```

如果我们使用的是默认的 clone 方法，得到的就是一个对象的浅拷贝。这里的拷贝只会拷贝目标对象的基本类型属性，在这里 name 和 age 会被真正拷贝一份，而 president 是引用类型，所以只会拷贝一份引用，本质上还是指向同一个 President 对象。如果我们这时将 president 引用所指向的 President 对象的名字改了，那么会反应到 school 和 schoolCopy 上，但是如果我们只是改变了其中一个对象的 age 属性值，那么另一个对象的 age 属性值是不会发生变化的，示例如下：

```java
School school = new School("WHU", 120, new President("李晓红"));
School school_copy = (School) school.clone();  // 浅拷贝

System.out.println(school.getPresident().hashCode() + "\t" + school_copy.getPresident().hashCode());
school.getPresident().setName("李达");

System.out.println("origin:" + school);
System.out.println("copy:" + school_copy);
```

输出如下：

```text
258952499    258952499
origin:HUST,120,李达
copy:WHU,120,李达
```

##### 深拷贝

如果我们希望真正的得到 school 对象的拷贝，即字段 name、age 和 president 都是专属的，那么就需要修改默认的 clone 方法来达到目的。具体的思路就是在 School 类的 clone 方法里面调用 President 的 clone 方法，实现如下：

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
258952499    603742814
origin:WHU,120,李达
copy:WHU,120,李晓红
```

可以看出通过这种方式深拷贝后，就得到了真正意义上的两份 school 对象。除了上面这种方式，我们还可以 __基于 java 的序列化和反序列化机制实现深拷贝__（具体实现如下），不过需要注意的是 java 的序列化还是比较耗时的，所以一般不推荐这种方式。

```java
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
