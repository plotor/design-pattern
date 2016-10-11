package org.zhenchao.singleton;

/**
 * @author zhenchao.wang 2016-10-09 23:09
 * @version 1.0.0
 */
public class ChildRegisterSingleton extends RegisterSingleton {

    public ChildRegisterSingleton() {
    }

    public ChildRegisterSingleton getInstance() {
        return (ChildRegisterSingleton) RegisterSingleton.getInstance("org.zhenchao.singleton.ChildRegisterSingleton");
    }
}
