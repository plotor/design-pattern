package org.zhenchao.memento;

/**
 * @author zhenchao.wang 2019-11-27 14:02
 * @version 1.0.0
 */
public class Originator {

    // 以内部类的方式实现
    private static class MementoImpl implements Memento {

        private String state;

        MementoImpl(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    // 生成者状态信息
    private String state;

    private Memento createMemento() {
        // 生成备忘录对象
        return new MementoImpl(state);
    }

    private void restoreFromMemento(Memento memento) {
        // 从备忘录对象中恢复状态
        MementoImpl mementoImpl = (MementoImpl) memento;
        this.state = mementoImpl.getState();
    }

}
