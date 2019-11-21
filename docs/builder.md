### 生成器模式

- [典型生成器模式](#典型生成器模式)
    - [角色](#角色)
    - [示例](#示例)
- [简化的生成器模式](#简化的生成器模式)
    - [角色上的简化](#角色上的简化)
    - [采用静态内部类进一步简化](#采用静态内部类进一步简化)

生成器（Builder）模式同样是用来负责创建对象，不同于工厂模式，生成器模式更加注重对象创建的过程，适用于创建复杂的对象。

#### 典型生成器模式

在生成器模式中，我们可以定义好对象的创建过程，而具体创建的实现可以交给具体的生成器实现类完成，采用不同的生成器实现会创建不同的对象。

##### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
抽象生成器 | Builder | 抽象类 / 接口 | 负责定义生成实例的 API
具体生成器 | ConcreteBuilder | 类 | 继承或实现 Builder，并实现 Builder 中定义的 API，用于生成具体的产品
指导者 | Director | 类 | 使用 Builder 中的 API 来生成产品，并不依赖于 ConcreteBuilder
产品 | Product | 类 | 生成器生成的目标产品


我们可以在 __抽象生成器__ 中定义好一个 __产品__ 创建所需要的所有步骤，并由 __具体生成器__ 去实现，而 __指导者__ 则约束了整一个产品创建的过程。

##### 示例

考虑这样一个例子，假设我们需要设计一个信息生成器，用于生成各种信息（短信、电子邮件、彩信等等），其中信息就是我们需要生成的产品。一个信息大致分为收信人、主题、正文、附件、发信人几个部分，我们可以在抽象生成器中对信息的各个部分的设置进行声明，并由具体的生成器去实现这些 API。比如，我们可以定义短信生成器、电子邮件生成器、彩信生成器等等，而指导者则对生成器声明的各个部分进行组织，封装一个信息创建的过程。具体实现如下：

- 产品

```java
public class Message implements Product {

    private String receiver;
    private String subject;
    private String body;
    private Object attachment;
    private String sender;

    public Message() {
    }

    public Message(String receiver, String body, String sender) {
        this.receiver = receiver;
        this.body = body;
        this.sender = sender;
    }

    // ... getter & setter
}
```

Product 是一个标记接口，如果希望抽象所有产品，则可以定义一个这样的接口，也可以不需要。

- 抽象生成器

```java
public interface MessageBuilder {

    void setReceiver(String receiver);

    void setSubject(String subject);

    void setBody(String body);

    void setAttachment(Object attachment);

    void setSender(String sender);

    Message getMessage();

}
```

我们在抽象生成器中声明了构造一条信息所需的各个步骤，最后还提供了一个 `MessageBuilder#getMessage` 方法，用于获取信息（产品）对象。

- 具体生成器：短信生成器

```java
public class SmsBuilder implements MessageBuilder {

    private Message sms = new Message();

    @Override
    public void setReceiver(String receiver) {
        this.sms.setReceiver(receiver);
    }

    @Override
    public void setSubject(String subject) {
        // 短信不需要设置主题
    }

    @Override
    public void setBody(String body) {
        this.sms.setBody(body);
    }

    @Override
    public void setAttachment(Object attachment) {
        // 短信不需要设置附件
    }

    @Override
    public void setSender(String sender) {
        this.sms.setSender(sender);
    }

    @Override
    public Message getMessage() {
        return this.sms;
    }
}
```

- 具体生成器：电子邮件生成器

```java
public class EmailBuilder implements MessageBuilder {

    private Message email = new Message();

    @Override
    public void setReceiver(String receiver) {
        this.email.setReceiver(receiver);
    }

    @Override
    public void setSubject(String subject) {
        this.email.setSubject(subject);
    }

    @Override
    public void setBody(String body) {
        this.email.setBody(body);
    }

    @Override
    public void setAttachment(Object attachment) {
        this.email.setAttachment(attachment);
    }

    @Override
    public void setSender(String sender) {
        this.email.setSender(sender);
    }

    @Override
    public Message getMessage() {
        return this.email;
    }
}
```

- 指导者

```java
public class MessageDirector {

    private MessageBuilder builder;

    public MessageDirector(MessageBuilder builder) {
        this.builder = builder;
    }

    public Message build(
            String receiver, String subject, String body, Object attachment, String sender) {
        builder.setReceiver(receiver);
        builder.setSubject(subject);
        builder.setBody(body);
        builder.setAttachment(attachment);
        builder.setSender(sender);
        return builder.getMessage();
    }

}
```

指导者的 `MessageDirector#build` 方法封装了一个信息构造过程，同时支持用不同的 Builder 构造不同类型的信息。

最后我们通过客户端来生成信息：

```java
// 生成一条短信
MessageDirector smsDirector = new MessageDirector(new SmsBuilder());
Message sms = smsDirector.build("18888888888", "", "This is a short message!", "", "18511888811");

// 生成一封电子邮件
MessageDirector emailDirector = new MessageDirector(new EmailBuilder());
Message email = emailDirector.build(
        "zhenchao.wang@gmail.com", "builder pattern", "This is an email for you!", "x.jpg", "donna.z@gmail.com");
```

通过上面的示例，可以体会到生成器模式包含如下特点：

1. 封装一个复杂对象的创建过程。
2. 允许通过多个步骤创建对象，并且可以改变创建的过程，而工厂模式在创建对象时只有一个步骤。
3. 隐藏了产品的内部实现。
4. 产品的具体生产是可替换的。

#### 简化的生成器模式

在实际开发中，我们往往不会采用上面这样复杂的设计，而是将生成器涉及的角色进一步合并，从而让代码更加简单，一个类，一行语句调用即可。

##### 角色上的简化

大部分时候可以砍去抽象生成器角色，只保留具体的生成器角色，并将指导者角色融入到客户端代码中。具体实现如下：

- 具体生成器

```java
public class MessageBuilder {

    private String receiver;
    private String subject;
    private String body;
    private Object attachment;
    private String sender;

    public MessageBuilder() { }

    public MessageBuilder setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public MessageBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MessageBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public MessageBuilder setAttachment(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    public MessageBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public Message build() {
        return new Message(this);
    }

}
```

不同于前面的信息生成器，这里我们采用 fluent 风格，在每一个 setter 方法中返回了当前生成器的对象，简化客户端调用。

- 客户端

```java
Message email = new MessageBuilder()
        .setReceiver("18888888888")
        .setBody("This is an email for you!")
        .setSubject("builder pattern")
        .setAttachment("hello.doc")
        .setSender("18511888811")
        .build();
```

不同于前面的客户端，此处我们在客户端中组织了信息的生成过程。生成器模式在定义 __对象的创建过程是确定不可变的__，这里有点违反该原则，不过模式最终还是为了代码的灵活性，不能教条主义。

##### 采用静态内部类进一步简化

对于上面的例子，我们还可以进一步简化，将具体生成器定义为静态内部类，融入到产品中去，从而进一步简化代码：

- 产品 & 具体生成器

```java
public class Message implements Product {

    public static class MessageBuilder {

        private String receiver;
        private String subject;
        private String body;
        private Object attachment;
        private String sender;

        private MessageBuilder() {
        }

        public MessageBuilder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public MessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageBuilder setAttachment(Object attachment) {
            this.attachment = attachment;
            return this;
        }

        public MessageBuilder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

    }

    public static MessageBuilder newBuilder() {
        return new MessageBuilder();
    }

    private String receiver;
    private String subject;
    private String body;
    private Object attachment;
    private String sender;

    private Message(MessageBuilder builder) {
        this.receiver = builder.receiver;
        this.subject = builder.subject;
        this.body = builder.body;
        this.attachment = builder.attachment;
        this.sender = builder.sender;
    }

    // ... getter
}
```

- 客户端

```java
// 生成一条彩信
Message message = Message.newBuilder()
                .setReceiver("18888888888")
                .setBody("This is a multimedia message!")
                .setSubject("happy birthday")
                .setAttachment("birthday song.mp3")
                .setSender("18511888811")
                .build();
```
