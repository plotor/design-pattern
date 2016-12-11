package org.zhenchao.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.zhenchao.proxy.jproxy.TransactionUtils;

import java.lang.reflect.Method;

/**
 * 基于Cglib的代理
 *
 * @author zhenchao.wang 2016-12-11 10:45
 * @version 1.0.0
 */
public class DatabaseMethodInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        this.enhancer.setSuperclass(clazz);
        this.enhancer.setCallback(this);
        return enhancer.create(); // 动态创建代理实例
    }

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object obj;
        try {
            obj = methodProxy.invokeSuper(object, args);
            // 提交事务
            TransactionUtils.commit();
        } catch (Throwable e) {
            // 回滚事务
            TransactionUtils.rollback();
            throw e;
        }
        return obj;
    }

}
