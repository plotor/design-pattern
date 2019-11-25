package org.zhenchao.chain.pure;

/**
 * @author zhenchao.wang 2019-11-25 18:56
 * @version 1.0.0
 */
public class RejectHandler extends AssetsHandler {

    @Override
    public void handle(long employeeId, long assertsAmount) {
        // 拒绝申请
        System.out.println("reject this apply, employeeId: " + employeeId + ", asserts: " + assertsAmount);
    }

}
