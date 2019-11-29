package org.zhenchao.flyweight.sincere;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 客户端
 *
 * @author zhenchao.wang 2017-04-09 22:04
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();

        VisitorFlyweight flyweight = factory.getFlyweight(VisitorType.DEFAULT);
        flyweight.createOrRecoverVisitor(randomId());

        flyweight = factory.getFlyweight(VisitorType.FID);
        flyweight.createOrRecoverVisitor(randomId());

        flyweight = factory.getFlyweight(VisitorType.DEVICE);
        flyweight.createOrRecoverVisitor(randomId());
    }

    private static String randomId() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

}
