package org.zhenchao.responsibility.pure;

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
