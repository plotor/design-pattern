package org.zhenchao.proxy.jproxy;

import java.lang.reflect.Proxy;

/**
 * 客户端
 *
 * @author zhenchao.wang 2016-12-11 10:25
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        DatabaseDao databaseDao = new DatabaseDaoImpl();

        DatabaseDao databaseDaoProxy = (DatabaseDao) Proxy.newProxyInstance(
                databaseDao.getClass().getClassLoader(),
                databaseDao.getClass().getInterfaces(),
                new DatabaseDaoProxy(databaseDao));

        databaseDaoProxy.insert();

        databaseDaoProxy.delete();

        databaseDaoProxy.select();

        databaseDaoProxy.update();
    }

}
