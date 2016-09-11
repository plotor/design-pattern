package org.zhenchao.chain_of_responsibility.pure;

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
