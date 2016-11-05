### 一. 适配器与模式
&emsp;&emsp;适配器是我们日常生活中的比较常见的一个物件，比如Android和IOS充电线的转接头、HDMI到VGA的转接器等等，其作用是在不兼容的场景下提供一种兼容的解决方案。  
&emsp;&emsp;在程序设计中，我们将逻辑抽象成为一个个模块，通过对外暴露接口来提供服务，当需求方调用我们的接口的时候也会存在不兼容的情况，这个时候我们就可以通过适配器模式，在不改变原有代码的前提下，去解决兼容性问题。  
&emsp;&emsp;在适配器模式的实现中包含 __对象适配器__ 和 __类适配器__ 两种，前者利用组合将被适配的类连接到适配器中进行兼容性转换，而后者则是基于继承（更准确的说是多重继承）拿到被适配的类和目标类的接口进行兼容性转换。

### 二. 对象适配器模式
&emsp;&emsp;对象适配器通过组合的方式将被适配的类连接到适配器中，然后适配器负责适配的逻辑，适配器（Adapter）、被适配者（Adaptee），以及目标接口（Target）的关系如下图：  
![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/adapter-1.png?raw=true)
&emsp;&emsp;适配器通过持有被适配类的对象，并将其包装成目标接口，供需求方调用，从而可以在不修改原有代码的基础上，达到兼容的目的。  
&emsp;&emsp;举例说明，假设我们有一个操作Properties文件的类，但是我们的需求方却需要操作XML文件，这个时候我们就可以利用适配器模式，在不改变目标接口和原Properties文件操作类的前提下，通过在中间插入一个适配器来达到目的，最终效果是需求方仍然认为自己是在操作XML，而本质上是以操作XML的方式在操作Properties。实现代码如下：

- __Adaptee__

```java
/**
 * 抽象properties文件操作类
 *
 * @author zhenchao.wang 2016-11-05 11:00
 * @version 1.0.0
 */
public interface AbstractPropertiesHandler {

    /**
     * 加载properties
     *
     * @param filepath
     * @return
     */
    Properties loadProperties(String filepath);

    /**
     * 存储properties
     *
     * @param properties
     * @param filepath
     * @return
     */
    boolean saveProperties(Properties properties, String filepath);

}

/**
 * 一般properties文件处理类
 *
 * @author zhenchao.wang 2016-11-05 11:13
 * @version 1.0.0
 */
public class GeneralPropertiesHandler implements AbstractPropertiesHandler {

    public Properties loadProperties(String filepath) {
        // ... 文件加载逻辑
        return new Properties();
    }

    public boolean saveProperties(Properties properties, String filepath) {
        // ... 文件存储逻辑
        return true;
    }

}
```

- __Target__

```java
/**
 * 抽象XML文件操作类
 *
 * @author zhenchao.wang 2016-11-05 11:04
 * @version 1.0.0
 */
public interface AbstractXmlHandler {

    /**
     * 加载XML文件
     *
     * @param filepath
     * @return
     */
    Document loadXml(String filepath);

    /**
     * 存储XML文件
     *
     * @param document
     * @param filepath
     * @return
     */
    boolean saveXml(Document document, String filepath);

}
```

- __Adapter__

```java
/**
 * 适配器
 *
 * @author zhenchao.wang 2016-11-05 11:15
 * @version 1.0.0
 */
public class PropertiesToXmlAdapter implements AbstractXmlHandler {

    private AbstractPropertiesHandler abstractPropertiesHandler;

    public PropertiesToXmlAdapter(AbstractPropertiesHandler abstractPropertiesHandler) {
        this.abstractPropertiesHandler = abstractPropertiesHandler;
    }

    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = abstractPropertiesHandler.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return abstractPropertiesHandler.saveProperties(properties, filepath);
    }

}
```

### 三. 类适配器模式
&emsp;&emsp;对象适配器基于组合，而类适配器则是基于继承，准确的说应该是多继承，不过java对于多重继承不提供支持，所以这一实现方式相对于对象适配器在java中使用范围要少很多，针对一些场景还是可以采用这种方式，类适配器模式中的适配器（Adapter）、被适配者（Adaptee），以及目标接口（Target）的关系如下图：
![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/adapter-2.png?raw=true)  
适配器实现了目标接口，并继承被适配器类，并在适配器中实现兼容逻辑，还是考虑上面例子，采用类适配器模式实现如下：

- __Adapter__

```java
/**
 * 适配器
 *
 * @author zhenchao.wang 2016-11-05 11:15
 * @version 1.0.0
 */
public class PropertiesToXmlAdapter extends GeneralPropertiesHandler implements AbstractXmlHandler {

    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = super.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return super.saveProperties(properties, filepath);
    }

}
```
`GeneralPropertiesHandler`和`AbstractXmlHandler`的实现同上。

### 四. 单向与双向适配器
&emsp;&emsp;上面介绍的适配器都是单向的适配器，即将被适配的类适配满足目标接口，这里面Target和Adaptee角色是确定，实际上这个两个角色是可以互换的，而这两个角色的互换可以通过双向适配器来完成，从而实现双向的兼容，具体实现如下：

- __双向适配器__

```java
/**
 * 双向适配器
 *
 * @author zhenchao.wang 2016-11-05 11:33
 * @version 1.0.0
 */
public class TwoDirectAdapter implements AbstractPropertiesHandler, AbstractXmlHandler {

    private AbstractPropertiesHandler propertiesHandler;

    private AbstractXmlHandler xmlHandler;

    public TwoDirectAdapter(AbstractPropertiesHandler propertiesHandler, AbstractXmlHandler xmlHandler) {
        this.propertiesHandler = propertiesHandler;
        this.xmlHandler = xmlHandler;
    }

    public Document loadXml(String filepath) {
        Document document = null;
        Properties properties = propertiesHandler.loadProperties(filepath);
        // ... 执行properties到document的转换逻辑
        return document;
    }

    public boolean saveXml(Document document, String filepath) {
        Properties properties = null;
        // ... 执行document到properties的转换逻辑
        return propertiesHandler.saveProperties(properties, filepath);
    }

    public Properties loadProperties(String filepath) {
        Properties properties = null;
        Document document = xmlHandler.loadXml(filepath);
        // ... 执行document到properties的转换逻辑
        return properties;
    }

    public boolean saveProperties(Properties properties, String filepath) {
        Document document = null;
        // ... 执行properties到document的转换逻辑
        return xmlHandler.saveXml(document, filepath);
    }

}
```
`AbstractPropertiesHandler`和`AbstractXmlHandler`的实现同上。