package org.zhenchao.proxy.jproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 数据库操作代理
 *
 * @author zhenchao.wang 2016-12-11 10:14
 * @version 1.0.0
 */
public class DatabaseDaoProxy implements InvocationHandler {

    /** 被代理对象 */
    private DatabaseDao databaseDao;

    public DatabaseDaoProxy(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj;
        try {
            obj = method.invoke(databaseDao, args);
            // 提交事务
            TransactionUtils.commit();
        } catch (Throwable t) {
            // 回滚事务
            TransactionUtils.rollback();
            throw t;
        }
        return obj;
    }

}
