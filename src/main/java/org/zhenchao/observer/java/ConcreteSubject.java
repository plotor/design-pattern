package org.zhenchao.observer.java;

import java.util.Observable;

/**
 * 基于java原生支持的具体主题实现
 *
 * @author zhenchao.wang 2017-04-21 16:28
 * @version 1.0.0
 */
public class ConcreteSubject extends Observable {

    private String data = "";

    public String getData() {
        return data;
    }

    public ConcreteSubject setData(String data) {
        this.data = data;
        this.notifyObservers();
        return this;
    }

}
