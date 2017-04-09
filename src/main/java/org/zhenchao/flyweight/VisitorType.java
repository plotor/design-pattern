package org.zhenchao.flyweight;

/**
 * 游客类型
 *
 * @author zhenchao.wang 2017-04-09 18:31
 * @version 1.0.0
 */
public enum VisitorType {

    DEFAULT(0),

    FID(1),

    DEVICE(2);

    private int id;

    VisitorType(int id) {
        this.id = id;
    }
}
