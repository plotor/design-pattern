### 代理模式

- [角色](#角色)
- [静态代理](#静态代理)
- [Java 中的代理模式](#java-中的代理模式)

---

代理（Proxy）模式属于程序设计中比较常用的设计模式，用于为被代理对象提供一种代理机制以控制对被代理对象的访问。客户端直接与代理对象进行交互，而不能够接触真实对象。实际上代理对象接收到到客户端的请求之后，本质上还是交给真实对象去处理，不过因为请求需要先被代理对象拦截，所以我们可以在这个代理对象上去实现一些增强的逻辑。

代理模式有很多种，但是常见的主要有远程代理、虚拟代理，以及保护代理 3 类。

- __远程代理__：远程代理利用一个本地代理对象通过网络去代理远方的真实对象，从而可以让客户端无感知网络请求的存在，像是在操作一个本地对象一样去交互。RPC（Remote Procedure Call）是典型的远程代理技术。
- __虚拟代理__：虚拟代理在我们日常生活中也比较常见，加载页就是虚拟代理的一个典型代表。为了提升用户体验，程序通常会在比较耗时的操作上先展示一个加载页，并另起线程执行耗时操作，在这个过程中加载页可以被看做是一个虚拟代理，针对用户请求的一个表面响应。
- __保护代理__：我们常听到的反向代理技术就是保护代理的一种实现，例如，通过反向代理来实现对资源服务器的保护，在反向代理保护服务器的流程中，客户端向反向代理命名空间中的内容发送普通请求，接着反向代理将判断向何处(原始服务器)转交请求，并将获得的内容返回给客户端。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
主体 | Subject | 接口 | 定义代理类和被代理类的 API，以屏蔽底层具体是依赖于代理类还是被代理类
具体主体 | RealSubject | 类 | 被代理类实现，实现 Subject 接口
代理 | Proxy | 类 | 代理类实现，实现 Subject 接口，与客户端进行交互，并尽量自己处理来自客户端的请求，只有当自己处理不了时才委托给 RealSubject 进行处理，但是这一过程对客户端来说是透明的

示例实现：

- 主体接口

```java
public interface Subject {
    void request();
}
```

- 被代理类

```java
public class RealSubject implements Subject {

    @Override
    public void request() {
        // 具体处理逻辑
    }

}
```

- 代理类

```java
public class Proxy implements Subject {

    /** 被代理对象 */
    private RealSubject subject;

    public Proxy(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        // 前置处理，可选

        // 委托被代理对象
        subject.request();

        // 后置处理，可选
    }

}
```


接口 Subject 声明了 API，用于约束 RealSubject 和 Proxy 的行为。RealSubject 是被代理类，而 Proxy 是代理类，用于代理和控制访问被代理类。

#### 静态代理

下面来看具体的例子，在伟大的天朝怎么能够少了代理呢，不然我们怎么能够愉快的拥抱互联网。我们的互联网是被 GFW 包围着，如果想要看看外面的世界就必须用到代理，代理就像是一个梯子，让我们可以翻过高高的墙。

- 主体：HTTP 请求

```java
public interface HttpRequest {
    String get(String url) throws Exception;
}
```

- 主体实现

```java
public class HttpRequestImpl implements HttpRequest {

    @Override
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
            response.append(inputLine).append("\n");
        }
        in.close();

        return response.toString();
    }

}
```

似乎一切都已经 OK 了，我们现在可以利用上面的方法愉快的发起 HTTP 请求了，小小测试一下：

```java
String url = "https://www.google.com";
HttpRequest httpRequest = new HttpRequestImpl();
try {
    System.out.println(httpRequest.get(url));
} catch (Exception e) {
    e.printStackTrace();
}
```

程序输出：

```text
Sending 'GET' request to URL : http://www.zhenchao.org
Response Code : 200
...
```

访问一下 Google 看看，输出如下：

```text
java.net.ConnectException: Connection timed out: connect
...
```

好吧，得让代理模式大显身手啦。

- 代理：HTTP 请求代理

```java
public class HttpRequestProxy implements HttpRequest {

    /** 被代理对象 */
    private HttpRequest httpRequest;

    public HttpRequestProxy(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public String get(String url) throws Exception {
        // 走 Socks5 代理
        System.getProperties().put("proxyHost", "127.0.0.1");
        System.getProperties().put("proxyPort", "1080");
        System.getProperties().put("proxySet", "true");
        return this.httpRequest.get(url);
    }

}
```

这里我们基于 SOCKS5 代理，通过 shadowsocks 做转发。实际上，shadowsocks 本质上也是一个代理，它将原来 SSH 创建的 SOCKS5 协议拆开成 server 端和 client 端，如下图所示：

![image](https://github.com/plotor/plotor.github.io/blob/master/images/2016/shadowsocks.png?raw=false)

客户端发出的请求基于 SOCKS5 协议跟 ss-local 端进行通讯，由于这个 ss-local 一般是本机或路由器或局域网的其他机器，不经过 GFW，所以解决了上面被 GFW 通过特征分析进行干扰的问题。ss-local 和 ss-server 两端通过多种可选的加密方法进行通讯，经过 GFW 的时候是常规的 TCP 包，没有明显的特征码，而且 GFW 也无法对通讯数据进行解密。ss-server 将收到的加密数据进行解密，还原原来的请求，再发送到用户需要访问的服务，获取响应原路返回。([更多关于 shadowsocks 运行机制的介绍](http://vc2tea.com/whats-shadowsocks/))

我们在代理中设置本地环境变量，让请求真实对象地请求打到本地 shadowsocks 代理，从而当我们使用 HttpRequestProxy 去请求指定链接的时候，可以翻过那座墙，再次 Google 一遍：

```java
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
```

如你所料，第一次访问失败，第二次基于代理的访问成功。整个流程我们直接交互的对象是代理对象，代理再通过真实对象去处理我们的请求，但是因为代理对象在中间进行了拦截，所以我们可以在代理里面做一些必要的工作，比如这里的走 shadowsocks 请求。如果是远程代理；那么我们可以在代理对象里面实现网络通信的细节，请求远处的被代理对象；如果是虚拟代理，那么我们可以在代理对象里面添加展示加载页的逻辑。总之在代理模式中，代理对象是与客户端直接交互的对象，而真正处理客户端请求的还是需要委托给被代理对象。

#### Java 中的代理模式

Java 在语言层面为代理模式提供了支持，java 中的代理分为静态代理和动态代理。上面这种我们手动去创建代理对象的方式称之为静态代理，java 也为我们提供了更加好用的动态代理支持，主要涉及类 `java.lang.reflect.InvocationHandler` 和 `java.lang.reflect.Proxy` 两个类。

考虑事务提交和回滚的例子，如果我们希望在方法层面提供对事务的支持，并且事务是可插拔的，那么除了像 Spring 那样基于注解外，我们也可以走代理机制，具体实现如下：

- 数据库操作

```java
public interface DatabaseDao {

    void insert() throws SQLException;

    void delete() throws SQLException;

    void update() throws SQLException;

    void select() throws SQLException;

}

public class DatabaseDaoImpl implements DatabaseDao {

    @Override
    public void insert() throws SQLException {
        System.out.println("insert into table");
    }

    @Override
    public void delete() throws SQLException {
        System.out.println("delete from table");
    }

    @Override
    public void update() throws SQLException {
        // 模拟异常
        System.out.println("update table set");
        throw new SQLException("update data exception");
    }

    @Override
    public void select() throws SQLException {
        System.out.println("select * from table");
    }

}
```

- __数据库操作代理__

```java
public class DatabaseDaoProxy implements InvocationHandler {

    /** 被代理对象 */
    private DatabaseDao databaseDao;

    public DatabaseDaoProxy(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj;
        try {
            obj = method.invoke(databaseDao, args);
            // 提交事务
            TransactionUtils.commit();
        } catch (Throwable t) {
            // 回滚事务
            TransactionUtils.rollback();
            throw t;
        }
        return obj;
    }

}
```

- __客户端__

```java
DatabaseDao databaseDao = new DatabaseDaoImpl();

// 创建代理对象
DatabaseDao databaseDaoProxy = (DatabaseDao) Proxy.newProxyInstance(
        databaseDao.getClass().getClassLoader(),
        databaseDao.getClass().getInterfaces(),
        new DatabaseDaoProxy(databaseDao));

/* 基于代理对象执行数据库操作 */

databaseDaoProxy.insert();
databaseDaoProxy.delete();
databaseDaoProxy.select();
databaseDaoProxy.update();
```

Java 动态代理为我们使用代理提供了极大便利，但是存在的缺点就是 java 的代理是基于接口的，也就是说我们需要代理的类必须抽象出一个接口，这对于已有的代码来说显得比较尴尬，这个时候我们可以采用 CGlib 的动态代理来满足要求，具体不在展开。
