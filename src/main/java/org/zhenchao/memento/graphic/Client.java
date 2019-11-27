package org.zhenchao.memento.graphic;

import org.zhenchao.memento.Caretaker;
import org.zhenchao.memento.Memento;

import java.util.concurrent.TimeUnit;

/**
 * @author zhenchao.wang 2019-11-27 14:26
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Gamer gamer = new Gamer(100);
        Caretaker caretaker = new Caretaker();
        // 保存初始状态
        caretaker.saveMemento(gamer.createMemento());
        for (int i = 0; i < 10; i++) {
            if (gamer.play()) {
                // 持有的金钱增多了，保存游戏状态
                Memento memento = gamer.createMemento();
                caretaker.saveMemento(memento);
            } else {
                // 持有的金钱变少或者没变，恢复到之前的状态
                Memento memento = caretaker.retriveMemento();
                gamer.restoreMemento(memento);
            }
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println("最终的金额： " + gamer.getMoney());
    }

}
