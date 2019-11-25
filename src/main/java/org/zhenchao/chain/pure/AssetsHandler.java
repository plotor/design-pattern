package org.zhenchao.chain.pure;

/**
 * 资产申请抽象处理器
 *
 * @author zhenchao.wang 2016-09-11 16:00
 * @version 1.0.0
 */
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
