### 一. 组合模式
&emsp;&emsp;在程序设计中，有时会利用树形结构表示“部分-整体”的层次关系，通常我们需要区别对待树中的叶子节点和树枝节点，针对不同节点不同处理，而 __组合模式__ 则让我们可以以统一的视角去看待层次结构中的各类节点。  
&emsp;&emsp;在整个模式中起到核心作用的是对树枝和树叶的抽象，抽象可以让我们一视同仁地去处理树枝和叶子，从而让上层程序看起来更加的透明，但是这也带来了安全性方面的问题，因为树枝和叶子虽然都是树形结构中的节点，但是也存在着功能和属性上的区别，完全的抽象易于对上层的透明，但是也会为树枝或叶子引入不要的功能，这些功能对于上层程序来说是“徒有其名”。因此，在透明性和安全性的侧重上，组合模式衍生出了 __透明式组合模式__ 和 __安全式组合模式__ 。  

### 二. 类目树问题
&emsp;&emsp;购物类网站的左边一般都会有一个类目树，用于为用户提供购物导航，类目树是一个典型的树形结构设计。假设我们现在需要建立一个服装的类目树，简单而言，仅包含男装和女装，而男装下面又分为夹克和衬衫，女装下面则进一步分为裙子和打底裤，下面逐步对这也一个树形结构进行设计。

#### 2.1 不使用模式的解决方案
&emsp;&emsp;如果不采用模式，我们可能会分别对类目树中的树枝和叶子建立对象，树枝的下一级可以继续是树枝，也可以是叶子，而叶子作为终端节点，则要简单一些，具体实现如下：

- __树枝节点__

```java
/**
 * 树枝节点
 *
 * @author zhenchao.wang 2016-11-06 20:48
 * @version 1.0.0
 */
public class Composite {

    private List<Composite> childComposites = new ArrayList<>();

    private List<Leaf> leafs = new ArrayList<>();

    private String name;

    public Composite(String name) {
        this.name = name;
    }

    public void addComposite(Composite composite) {
        childComposites.add(composite);
    }

    public void addLeaf(Leaf leaf) {
        this.leafs.add(leaf);
    }

    public void print(String parent) {
        System.out.println(parent + "-" + name);
        for (final Composite childComposite : childComposites) {
            childComposite.print("\t");
        }
        for (final Leaf leaf : leafs) {
            leaf.print("\t");
        }
    }
}
```

- __叶子节点__

```java
/**
 * 叶子节点
 *
 * @author zhenchao.wang 2016-11-06 20:44
 * @version 1.0.0
 */
public class Leaf {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void print(String parent) {
        System.out.println("\t" + parent + "-" + name);
    }

}
```

- __客户端__

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2016-11-06 21:00
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Composite root = new Composite("服装");

        Composite man = new Composite("男装");
        man.addLeaf(new Leaf("衬衫"));
        man.addLeaf(new Leaf("夹克"));
        root.addComposite(man);

        Composite woman = new Composite("女装");
        woman.addLeaf(new Leaf("裙子"));
        woman.addLeaf(new Leaf("打底裤"));
        root.addComposite(woman);

        root.print("");
    }
}
```

程序输出如下：

```text
-服装
	-男装
		-衬衫
		-夹克
	-女装
		-裙子
		-打底裤
```

&emsp;&emsp;上述实现的摘要问题在于，客户端在构造树形结构市，需要清楚知道自己当前操作的是树枝还是叶子，如果我们的节点类别再多一些，那么会给客户端造成困扰，并且不符合“开-闭”原则。

#### 2.2 透明式组合模式
&emsp;&emsp;针对2.1小节中存在的问题，我们可以通过设计一个抽象类来对树枝和叶子进行抽象，抽象类的设计如下：

- __抽象组件__

```java
/**
 * 抽象组件
 *
 * @author zhenchao.wang 2016-11-06 21:18
 * @version 1.0.0
 */
public interface AbstractComponent {

    /**
     * 打印
     *
     * @param parent
     */
    void print(String parent);

    /**
     * 添加组件
     *
     * @param component
     */
    default void addComponent(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除组件
     *
     * @param component
     */
    default void removeComponent(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取组件
     *
     * @param index
     * @return
     */
    default AbstractComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }
}
```

&emsp;&emsp;通过抽象组件，我们可以一视同仁对待树枝和叶子，这也可以让客户端调用时不去多加区分当前操作的具体对象，即对象对于客户端是透明的。上述程序采用了[java8的默认接口方法](https://my.oschina.net/wangzhenchao/blog/754852)。

- __树枝节点__

```java
/**
 * 树枝节点
 *
 * @author zhenchao.wang 2016-11-06 21:26
 * @version 1.0.0
 */
public class Composite implements AbstractComponent {

    private String name;

    private List<AbstractComponent> components = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void print(String parent) {
        System.out.println(parent + "-" + name);
        for (final AbstractComponent component : components) {
            component.print("\t");
        }
    }

    @Override
    public void addComponent(AbstractComponent component) {
        this.components.add(component);
    }

    @Override
    public void removeComponent(AbstractComponent component) {
        this.components.remove(component);
    }

    @Override
    public AbstractComponent getComponent(int index) {
        return index >= this.components.size() ? null : this.components.get(index);
    }

}
```

&emsp;&emsp;相对于2.1中树枝节点，这里的树枝节点也不需要再多考虑自己当前包含的是下一级树枝，还是终端叶子。

- __叶子节点__

```java
/**
 * 叶子
 *
 * @author zhenchao.wang 2016-11-06 21:34
 * @version 1.0.0
 */
public class Leaf implements AbstractComponent {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void print(String parent) {
        System.out.println("\t" + parent + "-" + name);
    }

}
```

- __客户端__

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2016-11-06 21:37
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        AbstractComponent root = new Composite("服装");

        AbstractComponent man = new Composite("男装");
        man.addComponent(new Leaf("衬衫"));
        man.addComponent(new Leaf("夹克"));
        root.addComponent(man);

        AbstractComponent woman = new Composite("女装");
        woman.addComponent(new Leaf("裙子"));
        woman.addComponent(new Leaf("打底裤"));
        root.addComponent(woman);

        root.print("");
    }
}
```

&emsp;&emsp;此处的客户端在操作树枝和叶子，统一以组件的形式对待，无需知道当前操作的具体视树枝还是叶子。

#### 2.3 安全式组合模式

&emsp;&emsp;2.2小节中的透明式组合模式存在一个问题就是抽象类中定义的方法，并不是所有组件都具备的，比如`addComponent`、`removeComponent`，以及`getComponent`等操作仅是树枝节点才具备的功能，对于叶子节点只能告诉客户端说自己不支持，所以客户端在调用叶子节点的这些“徒有其名”的功能时，就存在安全性问题，为了安全性起见，我们可以“不抽象的那么彻底”，而只是将所有节点共有的功能抽象出来，节点专属的功能则自行实现，不过这样一来又降低了透明性，所以具体哪种方式好，还需要视具体的应用场景而定，安全式组合模式实现：

- __抽象组件__

```java
/**
 * 抽象组件
 *
 * @author zhenchao.wang 2016-11-06 21:18
 * @version 1.0.0
 */
public interface AbstractComponent {

    /**
     * 打印
     *
     * @param parent
     */
    void print(String parent);
}
```

- __客户端__

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2016-11-06 21:37
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Composite root = new Composite("服装");

        Composite man = new Composite("男装");
        man.addComponent(new Leaf("衬衫"));
        man.addComponent(new Leaf("夹克"));
        root.addComponent(man);

        Composite woman = new Composite("女装");
        woman.addComponent(new Leaf("裙子"));
        woman.addComponent(new Leaf("打底裤"));
        root.addComponent(woman);

        root.print("");
    }
}
```

&emsp;&emsp;树枝节点和叶子节点的实现同2.2，因为仅对公共部分进行抽象，所以客户端在调用每个对象的方法时一定是该对象具备的功能，不会存在安全性问题，不过这样也让客户端不能透明的对待每一类对象节点。