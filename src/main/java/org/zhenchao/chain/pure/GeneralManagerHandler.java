package org.zhenchao.chain.pure;

/**
 * 总经理处理对象
 *
 * @author zhenchao.wang 2016-09-11 16:43
 * @version 1.0.0
 */
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
