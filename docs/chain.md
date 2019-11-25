### 职责链模式

职责链（Chain of Responsibility）模式的意图在于使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系，将这些对象连成一条链，并沿着这条链传递请求，直到有一个对象处理它为止。将多个请求处理者串成一条链，然后让请求在链上进行传递，如果处理者能够处理该请求则处理之，否则就继续往后传递，直到遇到一个能够胜任的处理者，或到达链尾。类似于击鼓传花，如果鼓声在继续则传递继续，当鼓声停止的那一刻，手持花束的人就是处理者。

#### 角色

组件 | 类名 | 类型 | 描述
--- | --- | --- | ---
处理器 | Handler | 抽象类 | 定义了处理请求的接口，也可以在这里实现后继链
具体处理器 | ConcreteHandler | 类 | 实现对职责范围内的请求的处理逻辑，如果超出职责范围则转发给后继处理器
请求者 | Client | 类 | 向职责链上的具体处理器提交请求，让职责链负责处理

- 处理器

```java
public abstract class Handler {

    /** 后继处理器 */
    protected Handler next;

    public abstract void handle();

    public void setNext(Handler next) {
        this.next = next;
    }

}
```

- 具体处理器

```java
public class ConcreteHandler extends Handler {

    @Override
    public void handle() {
        if (...) { // 在职责范围内
            // do handle
            return;
        }
        // 不再职责范围内，转给后继处理器
        if (null != next) {
            next.handle();
        }
    }

}
```

#### 示例

考虑日常资产申请的场景，平日我们申请资产时往往需要一个或多个上级领导签字，全部通过之后，资产才能批准下来，并且你申请的资产越昂贵，需要签字审批的人越多。假设我们对于资产的金额及其审批人定义如下：

- 500 元以下，项目经理审批
- 1000 元以下，部门经理审批
- 1500 元以下，总经理审批

如果不采用职责链模式去实现，那么可能的实现方式如下：

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

当业务逻辑简单的时候，上面的实现没有任何问题，但是设想如果审批环节有很多，那么我们需要写一个很长的 `if-else`。这样的代码不够简洁，当某一天我们需要去更改审批的流程，或者有些资产的申请不需要某个中间人时，我们需要去针对新的需求再重新写一遍有细微差别的 `if-else`，这样的代码显然不够灵活。基于单纯职责链的设计模式如下：

- 处理器

```java
public abstract class AssetsHandler {

    protected AssetsHandler nextHandler;

    public AssetsHandler() {
    }

    public AssetsHandler(AssetsHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(long employeeId, long assertsAmount);

    public void setNextHandler(AssetsHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected boolean validateEmployeeId(long employeeId) {
        // 验证雇员 ID 的合法性
        return true;
    }

}
```

- 具体处理器

```java
public class ProjectManagerHandler extends AssetsHandler {

    public ProjectManagerHandler() {
    }

    public ProjectManagerHandler(AssetsHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        // 仅审批 500 元以内的资产申请
        if (assertsAmount <= 500L) {
            if (this.validateEmployeeId(employeeId)) {
                System.out.println("Approved by project manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
            return;
        }

        // 超出 500 元，交给后继处理器
        if (null != nextHandler) {
            nextHandler.handle(employeeId, assertsAmount);
        }
    }

}

public class DepartmentManagerHandler extends AssetsHandler {

    public DepartmentManagerHandler() {
    }

    public DepartmentManagerHandler(AssetsHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        // 仅处理 1000 元以内的资产申请
        if (assertsAmount <= 1000L) {
            if (this.validateEmployeeId(employeeId)) {
                System.out.println("Approved by department manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
            return;
        }

        // 超出 1000 元，交给后继处理器
        if (null != nextHandler) {
            nextHandler.handle(employeeId, assertsAmount);
        }
    }

}

public class GeneralManagerHandler extends AssetsHandler {

    public GeneralManagerHandler() {
    }

    public GeneralManagerHandler(AssetsHandler next) {
        super(next);
    }

    @Override
    public void handle(long employeeId, long assertsAmount) {
        // 仅处理 1500 元以内的资产申请
        if (assertsAmount <= 1500L) {
            if (this.validateEmployeeId(employeeId)) {
                System.out.println("Approved by general manager!");
            } else {
                System.out.println("The employeeId is illegal!");
            }
            return;
        }

        // 超出 1500 元，交给后继处理器
        if (null != nextHandler) {
            nextHandler.handle(employeeId, assertsAmount);
        }
    }

}

public class RejectHandler extends AssetsHandler {

    @Override
    public void handle(long employeeId, long assertsAmount) {
        // 拒绝申请
        System.out.println("reject this apply, employeeId: " + employeeId + ", asserts: " + assertsAmount);
    }

}
```

- 请求者

```java
AssetsHandler assetsHandler =
        new ProjectManagerHandler(
                new DepartmentManagerHandler(
                        new GeneralManagerHandler(
                                new RejectHandler())));
assetsHandler.handle(123456L, 500L);
assetsHandler.handle(123456L, 1000L);
assetsHandler.handle(123456L, 1500L);
assetsHandler.handle(123456L, 2000L);
```

单纯的职责链模式有一个很明显的特点就是一个请求只能被一个处理者处理。绑定在一条链上的处理者对于请求要么处理，要么丢给后继处理者处理，当有一个处理者处理了该请求时，整个过程就结束了，所以不存在处理者既处理请求，又传递请求的情况。不过，这种需求是存在的，应该说大部分需求都不是简单的由一个处理者来处理，这个时候上面的单纯职责链就需要稍微修改一下，从而让一个处理者既处理请求，又传递请求。我们把这样的职责链称为不纯的，也可以叫做功能链模式。
