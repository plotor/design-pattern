package org.zhenchao.flyweight.sincere;

/**
 * 游客类型
 *
 * @author zhenchao.wang 2017-04-09 18:31
 * @version 1.0.0
 */
public enum VisitorType {

    /** 默认类型 */
    DEFAULT(0),

    /** 基于安全芯片 */
    FID(1),

    /** 基于设备IMEI信息 */
    DEVICE(2);

    private int id;

    VisitorType(int id) {
        this.id = id;
    }
}
