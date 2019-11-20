### 工厂模式

工厂模式属于日常开发中几个常用模式之一，__工厂是用来创建对象的地方__。在实际开发中，通常推荐面向接口编程，这样当我们期望替换具体实现类的时候，可以无须修改具体的调用细节，比如我们通常使用 `List list = new ArrayList();` 的方式来创建一个 List 对象，这样当我们哪天希望将 ArrayList 替换成 LinkedList 的时候，只需要修改对象的创建过程即可，但是如果我们的具体实现类有多个，而且具体创建哪个是在运行时决定的，那么我们的代码可能会写成下面这样：

```java
Parent parent;
if(a) {
    parent = new ChildA();
} else if(b) {
    parent = new ChildB();
} else {
    parent = new ChildC();
}
```

这样实现的一个很大的缺点是难以管理，设想当哪天我们需要加入一个 ChildD 的时候，还需要去代码中找到所有上面的代码块进行修改，万一遗漏了某些地方，那么将是很严重的 bug。这个时候我们就需要通过工厂模式来集中管理对象的创建过程。

#### 简单工厂模式

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象产品 | Product | 接口 / 抽象类 | 对产品的抽象
具体产品 | ConcreteProduct | 类 | 实现了 Product 抽象类的具体产品
工厂类 | Factory | 类 | 用于创建对象

设置抽象产品和具体产品角色是希望支持面向接口编程，而这也是工厂模式的基础。

##### 示例

假设我们有一个咖啡工厂，用于生产各类咖啡（e.g. 拿铁、摩卡、卡布奇洛），而具体生产哪种可以人为指定，那么如果不借用工厂模式，可能实现为：

```java
public void make(CoffeeType type) {

    Coffee coffee;
    if (CoffeeType.LATTE.equals(type)) {
        coffee = new Latte();
    } else if (CoffeeType.MOCHA.equals(type)) {
        coffee = new Mocha();
    } else if (CoffeeType.CAPPUCCINO.equals(type)) {
        coffee = new Cappuccino();
    } else {
        throw new IllegalArgumentException("unknown coffee type: " + type);
    }

    // ... 磨咖啡
    // ... 煮咖啡
    // ... 装杯

}
```
Z
如果工厂希望在新增一种咖啡类型，比如马琪雅朵（Machiatto），那么我们是不是需要去项目中找到所有创建咖啡对象的地方一一修改呢。如果此时你想到是难道我们不能将对象抽取到一个方法里面进行创建吗？很好，这个时候你已经有了简单工厂方法的概念了。下面我们来看看利用简单工厂模式如何去修改上面的代码：

```java
public void make(CoffeeType type) {

    // 利用简单工厂创建对象
    Coffee coffee = CoffeeFactory.build(type);

    // ... 磨咖啡
    // ... 煮咖啡
    // ... 装杯

}
```

借助于简单工厂模式，我们将对象的创建过程全部抽取到同一个方法中，这样以后对于扩展就只需要修改这个方法即可。需要说明的一点是，方法 make 不一定非要用 static 关键字修饰，这里只是为了图方便，但是需要清楚静态方法是不能被继承的。

简单工厂模式将所有的对象生成逻辑集中在一个方法中，这是它的优点也是缺点，当我们的具体产品较少，层次比较扁平的时候，借助于抽象工厂可以让代码更加简洁，但是如果我们具有相当数量的具体产品，产品结构还分层次，那么静态工厂将所有对象创建逻辑集中在一个方法中势必会让这个方法变得相当复杂、难以管理和扩展。虽然将该方法声明为非静态，我们可以通过集成来实现扩展，但是这样的类分工不明确，不是一个好的设计。

简单工厂模式全部实现如下：

- 工厂类：咖啡工厂

```java
public class CoffeeFactory {

    /**
     * 静态工厂方法
     *
     * @param coffee
     * @return
     */
    public static Coffee build(CoffeeType coffee) {
        if (CoffeeType.LATTE.equals(coffee)) {
            return new Latte();
        } else if (CoffeeType.MOCHA.equals(coffee)) {
            return new Mocha();
        } else if (CoffeeType.CAPPUCCINO.equals(coffee)) {
            return new Cappuccino();
        } else {
            throw new IllegalArgumentException("unknown coffee type: " + type);
        }
    }

}
```

- 抽象产品和具体产品

```java
public interface Coffee {

    String getName();

}

public class Latte implements Coffee {

    public String getName() {
        return "latte";
    }

}

public class Mocha implements Coffee {

    public String getName() {
        return "mocha";
    }

}

public class Cappuccino implements Coffee {

    public String getName() {
        return "cappuccino";
    }

}
```

#### 工厂方法模式

针对简单工厂模式在复杂代码逻辑中存在的问题，引出了工厂方法模式，工厂方法模式借助多态性，能够在保有简单工厂优点的同时，避免其缺点，让分工更加鲜明。工厂方法模式定义了一个创建对象的接口（即抽象工厂），由子类决定要创建哪一个类的对象，工厂方法把类的实例化延迟到子类中实现。

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象产品 | Product | 接口 / 抽象类 | 对产品的抽象
具体产品 | ConcreteProduct | 类 | 实现了 Product 抽象类的具体产品
抽象工厂 | Factory | 接口 | 用于创建对象
具体工厂 | ConcreteFactory | 类 | 实现了 Factory 接口，用于创建具体的产品对象

##### 示例

在工厂方法模式中，对象的创建不再由简单工厂独自去完成，而是将各个对象的创建交给具体工厂去完成，还是针对上面的生产咖啡问题，如果采用工厂方法模式，则实现如下（其中咖啡抽象，以及具体的咖啡实现同上）：

- 抽象工厂

```java
public interface AbstractCoffeeFactory {

    Coffee build();

}
```

- 具体工厂

```java
public class LatteCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Latte();
    }
}

public class MochaCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Mocha();
    }
}

public class CappuccinoCoffeeFactory implements AbstractCoffeeFactory {
    @Override
    public Coffee build() {
        return new Cappuccino();
    }
}
```

通过工厂方法模式让原本集中在一个方法中的所有对象创建过程，依据具体的类型下发到各个具体工厂，从而让工厂能够应对更加复杂的代码逻辑，分工也显得更加清晰，同时也符合“开闭”原则。

#### 抽象工厂模式

最开始接触抽象工厂模式，被它的名字所迷惑，以为是对于工厂模式的再一次改进，实际上抽象工厂模式解决的是另外一类问题，目的是使一个工厂可以创建多类对象，而这些对象之间存在一定的关联，或者说抽象工厂是 __创建多个内在关联的对象__，我们可以称之为 __对象族__。抽象工厂模式提供一个接口，该接口包含了一组方法，用于创建相关或依赖对象的家族，而无需明确指定具体的类。

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象工厂 | AbstractFactory | 抽象类 | 定义用于生成抽象产品的 API
具体工厂 | ConcreteFactory | 类 | 继承自 AbstractFactory，用于创建具体的产品
抽象产品 | Product | 接口 / 抽象类 | 定义 AbstractFactory 角色所生成的抽象零件和产品的 API
具体产品 | ConcreteProduct | 类 | 实现了 Product 的具体产品

##### 示例

还是用一个例子来说明，这次我们用汽车工厂，不用咖啡工厂啦。我们知道一辆汽车有多个部件组成，为了简单，这里我们假设一辆汽车仅由发动起、车架和轮胎组成。如果不使用抽象工厂，而是用工厂模式实现，那么会怎么样呢？我们会设计每一类零部件都由相应的工厂生产，最后由一个装配间统一装配，但是问题在于每一类部件都由多种多样的型号。比如，发动机有 V 式、Z 式，不同类型的车子需要的发动机还不一样，同样对于其他的部件也是一个道理，我们不能随意装配，这样是造不出来一辆车的，也就是说我们需要创建的对象之间是有内在关联的，这个时候我们就需要用到抽象工厂模式。

假设一个汽车工厂需要生产汽车、卡车等车型，那么就需要通过抽象工厂规定好各类车型需要的部件对象，所以我们可以在抽象工厂中设计 3 个方法分别用于提供发动机、车架，以及轮胎：

```java
public interface CarFactory {

    /**
     * 生产引擎
     *
     * @return
     */
    AbstractEngine buildEngine();

    /**
     * 生产车架
     *
     * @return
     */
    AbstractCarFrame buildFrame();

    /**
     * 生产轮子
     *
     * @return
     */
    AbstractWheel buildWheel();

    /**
     * 组装
     */
    default void assemble() {
        AbstractEngine engine = buildEngine();
        AbstractCarFrame frame = buildFrame();
        AbstractWheel wheel = buildWheel();
        engine.rotating();
        frame.strut();
        wheel.running();
    }

}
```

然后针对具体的车型分别设计相应的汽车工厂，并在工厂中关联具体的对象类型：

- 汽车工厂

```java
/**
 * 汽车制造工厂
 *
 * @author zhenchao.wang 2019-10-09 21:33
 * @version 1.0.0
 */
public class AutoFactory implements CarFactory {
    @Override
    public AbstractEngine buildEngine() {
        return new AutoEngine();
    }

    @Override
    public AbstractCarFrame buildFrame() {
        return new AutoFrame();
    }

    @Override
    public AbstractWheel buildWheel() {
        return new AutoWheel();
    }
}
```

- 卡车工厂

```java
/**
 * 卡车制造工厂
 *
 * @author zhenchao.wang 2019-10-09 21:35
 * @version 1.0.0
 */
public class TruckFactory implements CarFactory {
    @Override
    public AbstractEngine buildEngine() {
        return new TruckEngine();
    }

    @Override
    public AbstractCarFrame buildFrame() {
        return new TruckFrame();
    }

    @Override
    public AbstractWheel buildWheel() {
        return new TruckWheel();
    }
}
```

Engine、CarFrame，以及 Wheel 分别对应具体的产品。通过这样一种结构，就可以同时创建多个内在关联的对象，这是抽象工厂模式的目的。
