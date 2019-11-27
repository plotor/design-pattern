package org.zhenchao.memento;

/**
 * @author zhenchao.wang 2019-11-27 14:06
 * @version 1.0.0
 */
public class Caretaker {

    private Memento memento;

    public void saveMemento(Memento memento) {
        // 保存备忘录对象
        this.memento = memento;
    }

    public Memento retriveMemento() {
        // 获取被保存的备忘录对象
        return this.memento;
    }

}
