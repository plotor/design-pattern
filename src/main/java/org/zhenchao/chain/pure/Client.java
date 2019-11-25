package org.zhenchao.chain.pure;

/**
 * @author zhenchao.wang 2019-11-25 18:58
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        AssetsHandler assetsHandler =
                new ProjectManagerHandler(
                        new DepartmentManagerHandler(
                                new GeneralManagerHandler(
                                        new RejectHandler())));
        assetsHandler.handle(123456L, 500L);
        assetsHandler.handle(123456L, 1000L);
        assetsHandler.handle(123456L, 1500L);
        assetsHandler.handle(123456L, 2000L);
    }

}
