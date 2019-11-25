package org.zhenchao.chain.pure;

/**
 * 项目经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:13
 * @version 1.0.0
 */
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
