&emsp;&emsp;生成器模式也是用来负责创建对象，不同于侧重创建并返回对象的工厂模式，生成器模式则更加注重对象创建的过程，适用于创建复杂的对象。  

### 一. 典型生成器模式

&emsp;&emsp;在生成器模式中，我们可以定义好对象的创建过程，而具体每一步的实现可以交给具体的生成器，也就是说采用不同的生成器则会创建不同的对象。

&emsp;&emsp;生成器模式主要由如下几个角色构成：
- 抽象生成器
- 具体生成器
- 产品
- 指导者

&emsp;&emsp;我们可以在 __抽象生成器__ 中定义好一个 __产品__ 创建所需要的所有步骤，并由 __具体生成器__ 去实现，而 __指导者__ 则约束了整一个产品创建的过程。  
&emsp;&emsp;考虑这样一个例子，假设我们需要设计一个信息生成器，用于生成各种信息（短信、电子邮件、彩信等等），其中信息就是我们需要生成的产品，一个信息大致分为收信人、主题、正文、附件、发信人几个部分，我们可以在抽象生成器中对于信息的各个部分进行声明，并有具体的生成器去定义这些部分，比如我们可以定义短信生成器、电子邮件生成器、彩信生成器等等，而指导者则对生成器声明的各个部分进行组织，组成一个信息创建的过程，下面是具体实现：

- __产品__：信息 

```java
/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class Message implements Product {

    /** 收件人 */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 正文 */
    private String body;

    /** 附件 */
    private Object attachment;

    /** 发件人 */
    private String addresser;

    public Message() {
    }

    public Message(String receiver, String body, String addresser) {
        this.receiver = receiver;
        this.body = body;
        this.addresser = addresser;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    // 省略getter和setter
}
```
`Product`是一个标记接口，如果希望抽象所有产品，则可以定义一个这样的接口，也可以不需要。

- __抽象生成器__

```java
/**
 * 抽象信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public interface MessageBuilder {

    /**
     * 设置收信人
     */
    void setReceiver(String receiver);

    /**
     * 设置主题
     */
    void setSubject(String subject);

    /**
     * 设置正文
     */
    void setBody(String body);

    /**
     * 设置附件
     */
    void setAttachment(Object attachment);

    /**
     * 设置发件人
     */
    void setAddresser(String addresser);

    /**
     * 获取信息实例
     *
     * @return
     */
    Message retriveMessage();

}
```
我们在抽象生成器中声明了构造一条信息所需的各个步骤，最后还提供了一个`retriveMessage`方法，用于获取产品对象。

- __具体生成器__：短信、电子邮件

```java
/**
 * 短信生成器
 *
 * @author zhenchao.wang 2016-10-19 22:58
 * @version 1.0.0
 */
public class ShortMessageBuilder implements MessageBuilder {

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
    public void setAddresser(String addresser) {
        this.sms.setAddresser(addresser);
    }

    @Override
    public Message retriveMessage() {
        return this.sms;
    }
}

/**
 * 电子邮件生成器
 *
 * @author zhenchao.wang 2016-10-19 23:06
 * @version 1.0.0
 */
public class EmailBuilder implements MessageBuilder {

    Message email = new Message();

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
    public void setAddresser(String addresser) {
        this.email.setAddresser(addresser);
    }

    @Override
    public Message retriveMessage() {
        return this.email;
    }
}
```

- __指导者__：信息生成指导者

```java
/**
 * 信息生成指导者
 *
 * @author zhenchao.wang 2016-10-19 23:10
 * @version 1.0.0
 */
public class MessageDirector {

    private MessageBuilder messageBuilder;

    public MessageDirector(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    /**
     * 指导生成
     *
     * @param receiver
     * @param subject
     * @param body
     * @param attachment
     * @param addresser
     * @return
     */
    public Message build(String receiver, String subject, String body, Object attachment, String addresser) {
        messageBuilder.setReceiver(receiver);
        messageBuilder.setSubject(subject);
        messageBuilder.setBody(body);
        messageBuilder.setAttachment(attachment);
        messageBuilder.setAddresser(addresser);
        return messageBuilder.retriveMessage();
    }
}
```
指导者的`build`方法定义了一个信息生成的过程。

最后我们通过客户端来生成信息：
```java
// 生成一条短信
MessageDirector smsDirector = new MessageDirector(new ShortMessageBuilder());
Message sms = smsDirector.build("18888888888", "", "This is a short message!", "", "18511888811");

// 生成一封邮件
MessageDirector emailDirector = new MessageDirector(new EmailBuilder());
Message email = emailDirector.build("zhenchao.wang@gmail.com", "builder pattern", "This is an email for you!", "x.jpg", "donna.z@gmail.com");
```

通过上面的示例，可以得到生成模式包含如下几个特点：
1. 封装一个复杂对象的创建过程
2. 允许通过多个步骤创建对象，并且可以改变创建的过程，而工厂模式在创建对象时只有一个步骤
3. 隐藏了产品的内部实现
4. 产品的具体生产是可替换的 


### 二. 简化的生成器模式
&emsp;&emsp;在实际开发中，我们往往不会采用上面这样的复杂结果，而是将生成器涉及的角色进行进一步合并，从而让代码更加简单，一个类，一行语句调用即可。

#### 2.1 角色上的简化
&emsp;&emsp;依据需求，大部分时候可以砍去抽象生成器角色，从而只保留具体的生成器角色，并将指导者角色融入到客户端代码中，具体实现：

- __产品__：信息
 
```java
/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class MessageV2 implements Product {

    /** 收件人 */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 正文 */
    private String body;

    /** 附件 */
    private Object attachment;

    /** 发件人 */
    private String addresser;

    public MessageV2(MessageBuilderV2 builder) {
        this.receiver = builder.getReceiver();
        this.subject = builder.getSubject();
        this.body = builder.getBody();
        this.attachment = builder.getAttachment();
        this.addresser = builder.getAddresser();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    // 省略getter方法
}
```

- __具体生成器__：信息生成器

```java
/**
 * 信息生成器
 *
 * @author zhenchao.wang 2016-10-19 22:34
 * @version 1.0.0
 */
public class MessageBuilderV2 {

    /** 收件人 */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 正文 */
    private String body;

    /** 附件 */
    private Object attachment;

    /** 发件人 */
    private String addresser;

    public MessageBuilderV2(String receiver, String body, String addresser) {
        this.receiver = receiver;
        this.body = body;
        this.addresser = addresser;
    }

    /**
     * 设置收信人
     *
     * @param receiver
     * @return
     */
    public MessageBuilderV2 setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    /**
     * 设置主题
     *
     * @param subject
     * @return
     */
    public MessageBuilderV2 setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * 设置正文
     *
     * @param body
     * @return
     */
    public MessageBuilderV2 setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * 设置附件
     *
     * @param attachment
     * @return
     */
    public MessageBuilderV2 setAttachment(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    /**
     * 设置发件人
     *
     * @param addresser
     * @return
     */
    public MessageBuilderV2 setAddresser(String addresser) {
        this.addresser = addresser;
        return this;
    }

    public MessageV2 build() {
        return new MessageV2(this);
    }

    // 省略getter
}
```
不同于前面的信息生成器，这里我们在每一个过程方法中返回了当前生成器的对象，简化客户端调用。

- __客户端__：融合了指导者

```java
MessageBuilderV2 builderV2 = new MessageBuilderV2("18888888888", "This is an email for you!", "18511888811");
MessageV2 email = builderV2.setSubject("builder pattern").setAttachment("hello.doc").build();
```

&emsp;&emsp;不同于前面的客户端，此处我们在客户端中组织了信息的生成过程，生成器模式在定义 __对象的创建过程是确定不可变的__，这里有点违反该原则，不过模式最终还是为了代码的灵活性，不能教条主义。


#### 2.2 采用静态内部类进一步简化

&emsp;&emsp;对于上面的例子，我们还可以进一步简化，将具体生成器定义为静态内部类，融入到产品中去，从而进一步简化代码：

- __产品&具体生成器__

```java
/**
 * 信息
 *
 * @author zhenchao.wang 2016-10-19 22:47
 * @version 1.0.0
 */
public class MessageV3 implements Product {

    public static class MessageBuilder {
        /** 收件人 */
        private String receiver;

        /** 主题 */
        private String subject;

        /** 正文 */
        private String body;

        /** 附件 */
        private Object attachment;

        /** 发件人 */
        private String addresser;

        public MessageBuilder(String receiver, String body, String addresser) {
            this.receiver = receiver;
            this.body = body;
            this.addresser = addresser;
        }

        /**
         * 设置收信人
         *
         * @param receiver
         * @return
         */
        public MessageBuilder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        /**
         * 设置主题
         *
         * @param subject
         * @return
         */
        public MessageBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * 设置正文
         *
         * @param body
         * @return
         */
        public MessageBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        /**
         * 设置附件
         *
         * @param attachment
         * @return
         */
        public MessageBuilder setAttachment(Object attachment) {
            this.attachment = attachment;
            return this;
        }

        /**
         * 设置发件人
         *
         * @param addresser
         * @return
         */
        public MessageBuilder setAddresser(String addresser) {
            this.addresser = addresser;
            return this;
        }

        public MessageV3 build() {
            return new MessageV3(this);
        }

        // 省略getter
    }

    /** 收件人 */
    private String receiver;

    /** 主题 */
    private String subject;

    /** 正文 */
    private String body;

    /** 附件 */
    private Object attachment;

    /** 发件人 */
    private String addresser;

    public MessageV3(MessageBuilder builder) {
        this.receiver = builder.getReceiver();
        this.subject = builder.getSubject();
        this.body = builder.getBody();
        this.attachment = builder.getAttachment();
        this.addresser = builder.getAddresser();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    // 省略getter
}
```

- __客户端__

```java
// 生成一条彩信
MessageV3 mms = new MessageV3.MessageBuilder("18888888888", "This is a multimedia message!", "18511888811")
                .setSubject("happy birthday").setAttachment("birthday song.mp3").build();
```