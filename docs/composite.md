### 组合模式

- [角色](#角色)
- [示例](#示例)
    - [不使用模式](#不使用模式)
    - [透明式组合模式](#透明式组合模式)
    - [安全式组合模式](#安全式组合模式)

在程序设计中，有时会利用树形结构表示 “部分-整体” 的层次关系，通常我们需要区别对待树中的树叶结点和树枝结点，针对不同结点类型不同处理，而组合（Composite）模式则让我们可以用统一的视角去看待层次结构中的各类结点。

在整个模式中起到核心作用的是对树枝和树叶的抽象，从而让我们一视同仁地去处理树枝和树叶结点，从而让上层程序看起来更加的透明。不过，这也带来了安全性方面的问题，因为树枝和树叶虽然都是树形结构中的结点，但是也存在着功能和属性上的区别，完全的抽象易于对上层的透明，但是也会为树枝或树叶引入不必要的功能，这些功能对于上层程序来说是徒有其名。因此，在透明性和安全性的权衡上，组合模式衍生出了 __透明式组合模式__ 和 __安全式组合模式__ 。  

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
树叶 | Leaf | 类 | 树形结构中的树叶结点
复合物 | Composite | 类 | 可以在其中放入 Leaf 和 Composite 类型的结点
组件 | Component | 抽象类 | Leaf 和 Composite 的父类，也是对二者的抽象
客户端 | Client | 类 | 组合模式使用方

#### 示例

购物类网站的左边一般都会有一个类目树，用于为用户提供购物导航，类目树是一个典型的树形结构设计。假设我们现在需要建立一个服装的类目树，仅包含男装和女装。男装下面又分为夹克和衬衫，女装下面则分为裙子和打底裤，下面逐步对这一个树形结构进行设计。

##### 不使用模式

如果不采用任何设计模式，我们可能会分别对类目树中的树枝和树叶建立对象，树枝的下一级可以继续是树枝，也可以是树叶，而树叶作为终端结点，则要简单一些，具体实现如下：

- 树枝结点

```java
public class Composite {

    private String name;

    private List<Composite> childComposites = new ArrayList<>();
    private List<Leaf> leafs = new ArrayList<>();

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

- 树叶结点

```java
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

- 客户端

```java
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
```

上述实现的主要问题在于，客户端在构造树形结构时需要清楚知道自己当前操作的是树枝还是树叶结点，如果我们的结点类别再多一些，那么会给客户端造成困扰，并且不符合 “开-闭” 原则。

##### 透明式组合模式

针对不使用模式存在的问题，我们可以通过设计一个抽象类来对树枝和树叶进行抽象。抽象类的设计如下：

- 抽象组件

```java
public abstract class AbstractComponent {

    public abstract void print(String parent);

    public AbstractComponent get(int index) {
        throw new UnsupportedOperationException();
    }

    public void add(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(AbstractComponent component) {
        throw new UnsupportedOperationException();
    }

}
```

通过抽象组件，我们可以一视同仁对待树枝和树叶结点，也可以让客户端调用时无需区分当前操作的具体对象类型。

- 树枝结点

```java
public class Composite extends AbstractComponent {

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
    public AbstractComponent get(int index) {
        return index >= this.components.size() ? null : this.components.get(index);
    }

    @Override
    public void add(AbstractComponent component) {
        this.components.add(component);
    }

    @Override
    public void remove(AbstractComponent component) {
        this.components.remove(component);
    }

}
```

相对于不使用模式的树枝结点，这里的树枝结点也无需考虑自己当前包含的是下一级树枝，还是终端树叶结点。

- 树叶结点

```java
public class Leaf extends AbstractComponent {

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

- 客户端

```java
AbstractComponent root = new Composite("服装");

AbstractComponent man = new Composite("男装");
man.add(new Leaf("衬衫"));
man.add(new Leaf("夹克"));
root.add(man);

AbstractComponent woman = new Composite("女装");
woman.add(new Leaf("裙子"));
woman.add(new Leaf("打底裤"));
root.add(woman);

root.print("");
```

此处的客户端在操作树枝和树叶结点时，统一以组件 Component 的形式进行对待，无需知道当前操作的具体类型是树枝还是树叶。

##### 安全式组合模式

透明式组合模式存在一个问题就是抽象类中定义的方法，并不是所有具体组件都需要具备的。比如 `AbstractComponent#add`、`AbstractComponent#remove`，以及 `AbstractComponent#get` 等操作仅是树枝结点需要具备的功能，对于树叶结点只能告诉客户端说自己不支持，所以客户端在调用树叶结点的这些 “徒有其名” 的功能时，就存在安全性问题。为了安全性起见，我们可以 “不抽象的那么彻底”，而只是将所有组件共有的功能抽象出来，具体组件专属的功能则自行实现。不过这样一来又降低了透明性，所以具体哪种方式好，还需要视具体的应用场景而定。安全式组合模式实现如下：

- 抽象组件

```java
public abstract class AbstractComponent {

    public abstract void print(String parent);

}
```

- __客户端__

```java
Composite root = new Composite("服装");

Composite man = new Composite("男装");
man.add(new Leaf("衬衫"));
man.add(new Leaf("夹克"));
root.add(man);

Composite woman = new Composite("女装");
woman.add(new Leaf("裙子"));
woman.add(new Leaf("打底裤"));
root.add(woman);

root.print("");
```

树枝结点和树叶结点的实现同透明式组合模式，因为仅对公共部分进行抽象，所以客户端在调用每个对象的方法时一定是该对象所具备的功能，不会存在安全性问题，不过这样也让客户端不能透明的对待每一类对象结点。
