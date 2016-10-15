&emsp;&emsp;在本周的开发过程中遇到一个场景，多个方法在对结果封装上存在共性，但又不完全相同，假设对结果封装总共有A、B、C三个步骤，有的方法可能只需要其中几个步骤而不需要全部的步骤，有的方法在对结果封装顺序上可能不完全按照A->B->C的步骤进行，所以考虑将结果封装成一个函数的处理方法行不通，这个时候我想到了 __责任链模式__ 去应对这个场景，准确的说应该是不纯的责任链模式。

#### 单纯的责任链模式
```text
使多个对象都有机会处理请求，从而避免请求的发送者和接收之间的耦合关系，将这些对象连成一条链，并沿着这条链传递请求，直到有一个对象处理它为止。
```
&emsp;&emsp;上述是对单纯责任链模式的定义，将多个请求的处理者串成一条链，然后让请求在链上进行传递，如果处理者能够处理该请求，则处理之，否则就继续往后传递，直到有一个对象处理它或者到达链尾，类似于击鼓传花，如果鼓声在继续，那么传花继续，当鼓声停止的那一刻，手持花束的人就是处理者。  
&emsp;&emsp;考虑日常资产申请的场景，平日我们申请资产时，往往需要一个或多个上级进行签字，全部通过之后，资产才能批准下来，并且你申请的资产越昂贵，需要签字审批的人越多。假设我们对于资产的金额及其审批人定义如下：
```text
500元以下：项目经理审批
1000元以下：部门经理审批
1500元以下：总经理审批
```
如果不采用责任链模式去实现，那么可能的实现方式如下：
```java
public void assertsApproval(double amount) {
    if (amount <= 500) {
        // 项目经理审批
    } else if (amount <= 1000) {
        // 部门经理审批
    } else if (amount <= 1500) {
        // 总经理审批
    }
}
```
&emsp;&emsp;当业务逻辑简单的时候，上面的实现方式没有任何问题，但是设想如果审批环节有很多，那么我们需要写很长的`if-else`，这样的代码不够简洁，当某一天我们需要去更改审批的流程，或者有些资产的申请不需要项目经理时，那么我们需要去针对新的需求再重新写一遍有细微差别的`if-else`，这样的代码显然不够灵活，我也是在具体业务场景下看到前人的上百行`if-else`下才想到用责任链模式去重构。  
![img](https://github.com/ZhenchaoWang/zhenchaowang.github.io/blob/master/img/chain-of-responsibility.png?raw=true)  
&emsp;&emsp;上图展示了责任链模式的类设计，`AbstractAssertsHandler`抽象类为所有处理对象的抽象，具体处理者：`ProjectManagerHandler`, `DepartmentManagerHandler`, `GeneralManagerHandler`，分别对应项目经理、部门经理、总经理，这些经理分别处理对应金额的资产申请。每个经理都有一个`nextHandler`属性，用于设置自己的后继处理者，实现如下：
- AbstractAssetsHandler
```java
/**
 * 资产申请抽象处理器
 *
 * @author zhenchao.wang 2016-09-11 16:00
 * @version 1.0.0
 */
public abstract class AbstractAssetsHandler {

    /** 后置处理器 */
    protected AbstractAssetsHandler nextHandler;

    public AbstractAssetsHandler() {
    }

    public AbstractAssetsHandler(AbstractAssetsHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 处理函数
     *
     * @param employeeId 用户ID
     * @param assertsAmount 资产金额
     */
    public abstract void handle(long employeeId, long assertsAmount);

    public void setNextHandler(AbstractAssetsHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 验证雇员ID
     *
     * @param employeeId
     * @return
     */
    protected boolean validateEmployeeId(long employeeId) {
        return true;
    }
}
```
- ProjectManagerHandler
```java
/**
 * 项目经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:13
 * @version 1.0.0
 */
public class ProjectManagerHandler extends AbstractAssetsHandler {

    public ProjectManagerHandler() {
    }

    public ProjectManagerHandler(AbstractAssetsHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        if (assertsAmount <= 500) {
            if (validateEmployeeId(employeeId)) {
                System.out.println("Approved by project manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
        } else {
            if (null != nextHandler) {
                nextHandler.handle(employeeId, assertsAmount);
            }
        }
    }

}
```
- DepartmentManagerHandler
```java
/**
 * 部门经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:37
 * @version 1.0.0
 */
public class DepartmentManagerHandler extends AbstractAssetsHandler {

    public DepartmentManagerHandler() {
    }

    public DepartmentManagerHandler(AbstractAssetsHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        if (assertsAmount <= 1000) {
            if (validateEmployeeId(employeeId)) {
                System.out.println("Approved by department manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
        } else {
            if (null != nextHandler) {
                nextHandler.handle(employeeId, assertsAmount);
            }
        }
    }
}
```
- GeneralManagerHandler
```java
/**
 * 总经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:43
 * @version 1.0.0
 */
public class GeneralManagerHandler extends AbstractAssetsHandler {

    public GeneralManagerHandler() {
    }

    public GeneralManagerHandler(AbstractAssetsHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        if (assertsAmount <= 1500) {
            if (validateEmployeeId(employeeId)) {
                System.out.println("Approved by general manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
        } else {
            if (null != nextHandler) {
                nextHandler.handle(employeeId, assertsAmount);
            }
        }
    }
}
```
- 驱动类
```java
/**
 * 驱动类
 *
 * @author zhenchao.wang 2016-09-11 17:01
 * @version 1.0.0
 */
public class Main {

    public static void main(String[] args) {

        AbstractAssetsHandler assetsHandler = new ProjectManagerHandler(new DepartmentManagerHandler(new GeneralManagerHandler()));
        assetsHandler.handle(123456L, 500);
        assetsHandler.handle(123456L, 1000);
        assetsHandler.handle(123456L, 1500);

    }
}
```
#### 不纯的责任链模式&应用
&emsp;&emsp;单纯的责任链模式有一个很明显的特点就是一个请求只能被一个处理者处理，绑定在一条链上的处理者对于请求要么处理，要么丢给后继处理者处理，当有一个处理者处理了该请求时，整个过程结束，所以不存在处理者既处理请求，有把请求抛给后继处理者的情况，但是这种需求是存在的，应该说大部门需求都不是简单的由一个处理者来处理，这个时候上面的单纯的责任链就需要稍微修改一下，从而让一个处理者既处理请求，又传递请求，我们把这样的责任链称为“不纯的”，也可以叫做“功能链模式”。  
&emsp;&emsp;考虑最开始的需求：方法结果封装，我们可以通过不纯的责任链模式，让封装操作更加灵活、通用，实现如下：
- AbstractMethodResultHandler
```java
/**
 * 函数结果处理器抽象类
 *
 * @author zhenchao.wang 2016-09-11 16:47
 * @version 1.0.0
 */
public abstract class AbstractMethodResultHandler {

    protected AbstractMethodResultHandler nextHandler;

    public AbstractMethodResultHandler() {
    }

    public AbstractMethodResultHandler(AbstractMethodResultHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(Result result);

    public void setNextHandler(AbstractMethodResultHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
```
- ValueAHandler
```java
/**
 * Value A处理器
 *
 * @author zhenchao.wang 2016-09-11 16:55
 * @version 1.0.0
 */
public class ValueAHandler extends AbstractMethodResultHandler {

    public ValueAHandler() {
    }

    public ValueAHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性A的值
        result.setValA("value a");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}
```
- ValueBHandler
```java
/**
 * Value B处理器
 *
 * @author zhenchao.wang 2016-09-11 16:56
 * @version 1.0.0
 */
public class ValueBHandler extends AbstractMethodResultHandler {

    public ValueBHandler() {
    }

    public ValueBHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性B的值
        result.setValB("value b");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}
```
- ValueCHandler
```java
/**
 * Value C处理器
 *
 * @author zhenchao.wang 2016-09-11 17:56
 * @version 1.0.0
 */
public class ValueCHandler extends AbstractMethodResultHandler {

    public ValueCHandler() {
    }

    public ValueCHandler(AbstractMethodResultHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Result result) {
        // 设置属性C的值
        result.setValC("value c");

        // 继续处理
        if (null != nextHandler) {
            nextHandler.handle(result);
        }
    }
}
```
- 驱动函数
```java
/**
 * 驱动类
 *
 * @author zhenchao.wang 2016-09-11 17:01
 * @version 1.0.0
 */
public class Main {

    public static void main(String[] args) {

        AbstractMethodResultHandler resultHandler = new ValueAHandler(new ValueBHandler(new ValueCHandler()));
        Result result = new Result();
        resultHandler.handle(result);
        System.out.println(result);

    }
}
```
采用不纯的责任链模式重构之后，我们可以灵活的更改结果的处理顺序，以及灵活的选择结果的封装处理器，而不需要去改动结果的具体处理逻辑。