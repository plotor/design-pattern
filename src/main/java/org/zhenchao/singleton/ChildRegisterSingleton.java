package org.zhenchao.singleton;

/**
 * 子类
 *
 * @author zhenchao.wang 2016-10-09 23:09
 * @version 1.0.0
 */
public class ChildRegisterSingleton extends RegisterSingleton {

    public ChildRegisterSingleton() {
    }

    public static ChildRegisterSingleton getInstance() {
        return (ChildRegisterSingleton) RegisterSingleton.getInstance("org.zhenchao.singleton.ChildRegisterSingleton");
    }

}
