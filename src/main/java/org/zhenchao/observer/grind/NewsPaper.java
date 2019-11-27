package org.zhenchao.observer.grind;

import org.zhenchao.observer.Subject;

/**
 * @author zhenchao.wang 2019-11-27 09:48
 * @version 1.0.0
 */
public class NewsPaper extends Subject {

    private String content;

    public String getContent() {
        return content;
    }

    public NewsPaper setContent(String content) {
        this.content = content;
        // 通知所有的观察者，数据有更新了
        this.notifyObservers();
        return this;
    }

}
