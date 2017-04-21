package org.zhenchao.observer.java;

import org.apache.commons.lang3.StringUtils;

import java.util.Observable;

/**
 * 基于java原生支持的具体主题实现
 *
 * @author zhenchao.wang 2017-04-21 16:28
 * @version 1.0.0
 */
public class ConcreteSubject extends Observable {

    private String data = StringUtils.EMPTY;

    /**
     * 改变数据值
     *
     * @param data
     */
    public void changeData(String data) {
        if (!StringUtils.equals(this.data, data)) {
            this.data = data;
            this.setChanged();
        }
        this.notifyObservers();
    }

    public String getData() {
        return data;
    }
}
