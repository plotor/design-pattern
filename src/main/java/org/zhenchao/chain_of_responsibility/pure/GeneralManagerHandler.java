package org.zhenchao.chain_of_responsibility.pure;

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
