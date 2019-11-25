package org.zhenchao.chain.pure;

/**
 * 部门经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:37
 * @version 1.0.0
 */
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
