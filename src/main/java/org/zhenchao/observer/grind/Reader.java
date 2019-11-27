package org.zhenchao.observer.grind;

import org.zhenchao.observer.Observer;
import org.zhenchao.observer.Subject;

/**
 * @author zhenchao.wang 2019-11-27 09:52
 * @version 1.0.0
 */
public class Reader implements Observer {

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        NewsPaper np = (NewsPaper) subject;
        System.out.println(name + " 收到报纸更新的通知了，报纸摘要内容： " + np.getContent());
    }

}
