package org.zhenchao.responsibility.pure;

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
