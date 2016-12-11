package org.zhenchao.proxy.jproxy;

/**
 * 事务工具类
 *
 * @author zhenchao.wang 2016-12-11 10:18
 * @version 1.0.0
 */
public class TransactionUtils {

    /**
     * 提交事务
     */
    public static void commit() {
        System.out.println("commit transaction");
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        System.out.println("rollback transaction");
    }
}
