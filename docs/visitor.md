### 访问者模式

访问者表示一个作用于某对象结构中的各元素的操作，使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。访问者（Visitor）模式能够实现给已有对象透明添加新功能，而不需要对这些对象所属的类进行更改。在实现上需要按照访问者模式的结构，分离出两个类层次，一个是元素类层次，一个是访问者类层次。访问者模式一个典型的应用就是与组合模式一起，通过访问者模式给由组合模式构建的对象结构增加新功能。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
访问者 | Visitor | 接口 / 抽象类 |  对数据结构中每个具体的元素 ConcreteElement 声明一个对应的 `Visitor#visit(ConcreteElement element)` 方法，用于处理对应的 ConcreteElement 对象，具体处理的实现交由 ConcreteVisitor 完成
具体访问者 | ConcreteVisitor | 类 | 实现 Visitor 所声明的 API
元素 | Element | 接口 / 抽象类 | 表示需要被 Visitor 访问的对象，需要声明一个 `Element#accept(Visitor visitor)` 方法用于访问当前 ConcreteElement 对象
具体元素 | ConcreteElement | 类 | 实现 Element 所声明的 API
对象结构 | ObjectStructure | 类 | 通常包含多个被访问的对象，可以是一个复合或是一个集合，提供对这些对象遍历和处理的功能

各角色基于访问者模式的交互如下：

- 元素

```java
public abstract class Element {

    public abstract void accept(Visitor visitor);

}
```

- 具体元素

```java
public class ConcreteElement1 extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operation() {
        // 已有的功能实现
        System.out.println("concrete element 1 operation");
    }

}

public class ConcreteElement2 extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operation() {
        // 已有的功能实现
        System.out.println("concrete element 2 operation");
    }

}
```

- 访问者

```java
public interface Visitor {

    void visit(ConcreteElement1 element);

    void visit(ConcreteElement2 element);

}
```

- 具体访问者

```java
public class ConcreteVisitor implements Visitor {

    @Override
    public void visit(ConcreteElement1 element) {
        element.operation();
    }

    @Override
    public void visit(ConcreteElement2 element) {
        element.operation();
    }

}
```

- 对象结构

```java
public class ObjectStructure {

    private Collection<Element> elements = new ArrayDeque<>();

    public void handleRequest(Visitor visitor) {
        for (final Element element : elements) {
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        elements.add(element);
    }

}
```

- 客户端

```java
ObjectStructure os = new ObjectStructure();

// 创建并添加元素到对象结构中
os.add(new ConcreteElement1());
os.add(new ConcreteElement2());

// 创建访问者
Visitor visitor = new ConcreteVisitor();

// 调用业务处理的方法
os.handleRequest(visitor);
```

#### 示例

假设我们现在有一批客户，分为企业用户和个人用户，我们的公司具备处理这些客户和对客户进行偏好分析的功能。具体实现如下：

- 抽象客户

```java
public abstract class Customer {

    private String id;
    private String name;

    public abstract void accept(Visitor visitor);

    // ... getter & setter

}
```

- 具体客户

```java
public class PersonalCustomer extends Customer {

    private int age;

    public PersonalCustomer(String name) {
        this.setName(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // ... getter & setter

}

public class EnterpriseCustomer extends Customer {

    private String linkMan;

    public EnterpriseCustomer(String name) {
        this.setName(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // ... getter & setter
}
```

- 访问者

```java
public interface Visitor {

    void visit(PersonalCustomer customer);
    void visit(EnterpriseCustomer customer);

}
```

- 具体访问者

```java
public class ServiceRequestVisitor implements Visitor {

    @Override
    public void visit(PersonalCustomer customer) {
        System.out.println("处理客户 " + customer.getName() + " 提出服务要求");
    }

    @Override
    public void visit(EnterpriseCustomer customer) {
        System.out.println("处理 " + customer.getName() + " 企业提出服务要求");
    }

}

public class PreferenceAnalyzeVisitor implements Visitor {

    @Override
    public void visit(PersonalCustomer customer) {
        System.out.println("对个人客户 " + customer.getName() + " 进行产品偏好分析");
    }

    @Override
    public void visit(EnterpriseCustomer customer) {
        System.out.println("对企业客户 " + customer.getName() + " 进行产品偏好分析");
    }

}
```

- 对象结构

```java
public class ObjectStructure {

    private Collection<Customer> customers = new ArrayList<>();

    public void handleRequest(Visitor visitor) {
        for (final Customer customer : customers) {
            customer.accept(visitor);
        }
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

}
```

- 客户端

```java
ObjectStructure os = new ObjectStructure();

os.add(new EnterpriseCustomer("阿里巴巴"));
os.add(new EnterpriseCustomer("小米科技"));

os.add(new PersonalCustomer("张三"));
os.add(new PersonalCustomer("李四"));

// 客户提出服务请求
Visitor srVisitor = new ServiceRequestVisitor();
os.handleRequest(srVisitor);

// 对客户进行偏好分析
Visitor paVisitor = new PreferenceAnalyzeVisitor();
os.handleRequest(paVisitor);
```
