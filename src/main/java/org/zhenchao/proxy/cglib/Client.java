package org.zhenchao.proxy.cglib;

import org.zhenchao.proxy.jproxy.DatabaseDaoImpl;

/**
 * @author zhenchao.wang 2016-12-11 10:58
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        DatabaseMethodInterceptor methodInterceptor = new DatabaseMethodInterceptor();
        DatabaseDaoImpl databaseDaoProxy = (DatabaseDaoImpl) methodInterceptor.getProxy(DatabaseDaoImpl.class);

        databaseDaoProxy.insert();

        databaseDaoProxy.delete();

        databaseDaoProxy.select();

        databaseDaoProxy.update();
    }

}
