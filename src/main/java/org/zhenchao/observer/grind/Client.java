package org.zhenchao.observer.grind;

/**
 * @author zhenchao.wang 2019-11-27 09:54
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        // 创建一个报纸目标对象
        NewsPaper newsPaper = new NewsPaper();

        // 创建多个读者
        Reader reader1 = new Reader("张三");
        Reader reader2 = new Reader("李四");
        Reader reader3 = new Reader("王二");

        // 订阅报纸
        newsPaper.register(reader1);
        newsPaper.register(reader2);
        newsPaper.register(reader3);

        // 报纸内容更新，会通知所有订阅的读者
        newsPaper.setContent("观察者模式");
    }

}
