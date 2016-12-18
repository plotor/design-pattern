### 一. 代理模式

&emsp;&emsp;代理模式属于程序设计中比较常用的设计模式，通过为真实对象创建一个代理对象，并利用这个代理对象去代表真实对象。客户端直接与代理对象进行交互，而不能够接触真实对象，实际上代理对象接收到客户端的请求之后，本质上还是交给真实对象去处理，不过因为请求需要先被代理对象挡一刀，所以我们可以在这个代理对象上去实现一些增强逻辑。

&emsp;&emsp;代理模式有很多种，但是常见的主要有远程代理、虚拟代理，以及保护代理三类。

- 远程代理

&emsp;&emsp;远程代理利用一个本地代理对象通过网络去代理远方的真实对象，从而可以让客户端无感知网络的存在，而是像操作一个本地对象一样去交互。java中的RMI（Remote Method Invoke：远程方法调用）是典型的远程代理技术。

- 虚拟代理

&emsp;&emsp;虚拟代理在我们日常生活中也比较常见，“加载页”就是虚拟代理的一个典型代表。为了用户体验，程序通常会在比较耗时的操作上先展示一个加载页，并另起线程执行耗时操作，在这个过程中加载页可以被看做是一个虚拟代理，针对用户请求的一个“表面响应”。

- 保护代理

![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/reverse-proxy.jpg?raw=true)

&emsp;&emsp;我们常听到的反向代理技术就是保护代理的一种实现，例如通过反向代理来实现对资源服务器的保护，在反向代理保护服务器的流程中，客户端向反向代理的命名空间(name-space)中的内容发送普通请求，接着反向代理将判断向何处(原始服务器)转交请求，并将获得的内容返回给客户端。

![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/design-pattern-proxy.png?raw=true)

&emsp;&emsp;上图展示了设计模式的一般实现，接口`Subject`声明了方法，用于约束`RealSubject`和`Proxy`的行为。`RealSubject`是真实对象，而`Proxy`是这里的代理，用于代理和控制访问真实对象。通常需要被代理的真实对象包括：远程对象、创建开销大的对象，以及需要被保护的对象等。

&emsp;&emsp;下面来看具体的例子，在伟大的天朝怎么能够少了代理呢，不然我们怎么能够愉快的拥抱互联网啊。我们的互联网被万恶的GFW包围着，如果想要看看外面的世界，就必须用到代理，代理就像是一个“梯子”，让我们可以翻过高高的墙。

- __定义一个HTTP请求抽象接口__

```java
/**
 * HTTP请求抽象
 *
 * @author zhenchao.wang 2016-12-11 20:29
 * @version 1.0.0
 */
public interface HttpRequest {

    /**
     * 请求具体地址
     *
     * @param url
     */
    String get(String url) throws Exception;

}
```

这里我们简单定义了GET方法，用于请求一个具体的链接。

- __GET请求的具体实现__

```java
/**
 * HTTP请求的具体实现
 *
 * @author zhenchao.wang 2016-12-11 20:31
 * @version 1.0.0
 */
public class HttpRequestImpl implements HttpRequest{

    public String get(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // GET请求
        connection.setRequestMethod("GET");

        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();

        System.out.println("Sending 'GET' request to URL : " + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        return response.toString();
    }

}
```

我们通过`HttpURLConnection`发起HTTP请求。

似乎一切都已经OK，我们现在可以利用上面的方法愉快的发起HTTP请求了，小小测试一下：

```java
/**
 * 简单浏览器
 *
 * @author zhenchao.wang 2016-12-11 21:01
 * @version 1.0.0
 */
public class Browser {

    public static void main(String[] args) {

        String url = "https://www.google.com";

        HttpRequest httpRequest = new HttpRequestImpl();
        try {
            System.out.println(httpRequest.get(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
```
程序输出：

```text
Sending 'GET' request to URL : http://www.zhenchao.org
Response Code : 200
...
```

访问一下google看看，输出如下：

```text
java.net.ConnectException: Connection timed out: connect
...
```

好吧，得让代理模式大显身手啦，添加一个代理

- __HTTP请求代理__

```java
/**
 * HTTP请求代理
 *
 * @author zhenchao.wang 2016-12-11 22:16
 * @version 1.0.0
 */
public class HttpRequestProxy implements HttpRequest {

    private HttpRequest httpRequest;

    public HttpRequestProxy(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String get(String url) throws Exception {
        // 走Socks5代理
        System.getProperties().put("proxyHost", "127.0.0.1");
        System.getProperties().put("proxyPort", "1080");
        System.getProperties().put("proxySet", "true");
        return this.httpRequest.get(url);
    }

}
```

&emsp;&emsp;这里我们基于`SOCKS5`代理，通过`shadowsocks`做转发，`shadowsocks`本质上也是一个代理，它将原来SSH创建的`SOCKS5`协议拆开成server端和client端，如下图所示：

![image](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/shadowsocks.png?raw=true)

&emsp;&emsp;客户端发出的请求基于`SOCKS5`协议跟ss-local端进行通讯，由于这个ss-local一般是本机或路由器或局域网的其他机器，不经过GFW，所以解决了上面被GFW通过特征分析进行干扰的问题，ss-local和ss-server两端通过多种可选的加密方法进行通讯，经过GFW的时候是常规的TCP包，没有明显的特征码而且GFW也无法对通讯数据进行解密，ss-server将收到的加密数据进行解密，还原原来的请求，再发送到用户需要访问的服务，获取响应原路返回。([更多关于shadowsocks的信息](http://vc2tea.com/whats-shadowsocks/))


&emsp;&emsp;我们在代理中设置本地环境变量，让请求真实对象地请求打到本地shadowsocks代理，从而当我们使用`HttpRequestProxy`去请求指定链接的时候，可以翻过那座墙，再次google一遍：

```java
/**
 * 简单浏览器
 *
 * @author zhenchao.wang 2016-12-11 21:01
 * @version 1.0.0
 */
public class Browser {

    public static void main(String[] args) {

        String url = "https://www.google.com";

        // 不基于代理，直接访问
        HttpRequest httpRequest = new HttpRequestImpl();
        try {
            System.out.println(httpRequest.get(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 基于代理的访问
        httpRequest = new HttpRequestProxy(httpRequest);
        try {
            System.out.println(httpRequest.get(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
```

&emsp;&emsp;如你所料，第一次访问失败，第二次基于代理的访问成功。整个流程我们直接交互的对象是代理对象，代理再通过真实对象去处理我们的请求，但是因为代理在中间拦了一刀，所以我们可以在代理里面做一些必要的工作，比如这里的走shadowsocks请求，如果是远程代理，那么我们可以在代理对象里面实现网络通信的细节，请求远处真实的对象，如果是虚拟代理，那么我们可以在代理对象里面添加展示“加载页”的逻辑，总之在代理模式中，代理对象是与我们直接交互的对象，而真正处理我们请求的还是真实的对象。


### 二. java中的代理模式

&emsp;&emsp;java在语言层面为代理提供了支持，java中的代理分为静态代理和动态代理。上面这种我们手动去创建代理对象的方式称之为静态代理，java也为我们提供了更加好用的动态代理支持，java的动态代理主要涉及类`java.lang.reflect.InvocationHandler`和`java.lang.reflect.Proxy`，考虑事务提交和回滚的例子，如果我们希望在方法层面提供对事务的支持，并且事务是 __可插拔__的，那么除了像spring那样基于注解外，我们也可以走代理机制，具体实现如下：

- __数据库操作__

```java
/**
 * 数据库操作抽象
 *
 * @author zhenchao.wang 2016-12-11 10:06
 * @version 1.0.0
 */
public interface DatabaseDao {

    void insert() throws SQLException;

    void delete() throws SQLException;

    void update() throws SQLException;

    void select() throws SQLException;

}

/**
 * 数据库操作实现类
 *
 * @author zhenchao.wang 2016-12-11 10:11
 * @version 1.0.0
 */
public class DatabaseDaoImpl implements DatabaseDao {

    public void insert() throws SQLException {
        System.out.println("insert into table");
    }

    public void delete() throws SQLException {
        System.out.println("delete from table");
    }

    public void update() throws SQLException {
        // 模拟异常
        System.out.println("update table set");
        throw new SQLException("update data exception");
    }

    public void select() throws SQLException {
        System.out.println("select * from table");
    }

}
```

- __事务提交与回滚操作__

```java
/**
 * 事务工具类
 *
 * @author zhenchao.wang 2016-12-11 10:18
 * @version 1.0.0
 */
public class TransactionUtils {

    /**
     * 提交事务
     */
    public static void commit() {
        System.out.println("commit transaction");
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        System.out.println("rollback transaction");
    }
}
```

- __数据库操作代理__

```java
/**
 * 数据库操作代理
 *
 * @author zhenchao.wang 2016-12-11 10:14
 * @version 1.0.0
 */
public class DatabaseDaoProxy implements InvocationHandler {

    private DatabaseDao databaseDao;

    public DatabaseDaoProxy(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj;
        try {
            obj = method.invoke(databaseDao, args);
            // 提交事务
            TransactionUtils.commit();
        } catch (Throwable e) {
            // 回滚事务
            TransactionUtils.rollback();
            throw e;
        }
        return obj;
    }
}
```

- __客户端__

```java
/**
 * 客户端
 *
 * @author zhenchao.wang 2016-12-11 10:25
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        DatabaseDao databaseDao = new DatabaseDaoImpl();

        DatabaseDao databaseDaoProxy = (DatabaseDao) Proxy.newProxyInstance(
                databaseDao.getClass().getClassLoader(),
                databaseDao.getClass().getInterfaces(),
                new DatabaseDaoProxy(databaseDao));

        databaseDaoProxy.insert();

        databaseDaoProxy.delete();

        databaseDaoProxy.select();

        databaseDaoProxy.update();
    }

}
```

程序输出如下：

```text
insert into table
commit transaction

delete from table
commit transaction

select * from table
commit transaction

update table set
rollback transaction
```

&emsp;&emsp;java动态代理为我们使用代理提供了极大遍历，但是有一个缺点就是java的代理是基于接口的，也就是说我们需要代理的类必须抽象出一个接口，这对于已有的代码来说，有时候是一个门槛，这个时候我们可以采用cglib的动态代理来满足要求，下面针对上面的例子的cglib实现：

```java
/**
 * 基于Cglib的代理
 *
 * @author zhenchao.wang 2016-12-11 10:45
 * @version 1.0.0
 */
public class DatabaseMethodInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        this.enhancer.setSuperclass(clazz);
        this.enhancer.setCallback(this);
        return enhancer.create(); // 动态创建代理实例
    }

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object obj;
        try {
            obj = methodProxy.invokeSuper(object, args);
            // 提交事务
            TransactionUtils.commit();
        } catch (Throwable e) {
            // 回滚事务
            TransactionUtils.rollback();
            throw e;
        }
        return obj;
    }

}

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-12-11 10:58
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        DatabaseMethodInterceptor methodInterceptor = new DatabaseMethodInterceptor();
        DatabaseDaoImpl databaseDaoProxy = (DatabaseDaoImpl) methodInterceptor.getProxy(DatabaseDaoImpl.class);

        databaseDaoProxy.insert();

        databaseDaoProxy.delete();

        databaseDaoProxy.select();

        databaseDaoProxy.update();
    }

}
```

数据库操作的相关代码同前面。