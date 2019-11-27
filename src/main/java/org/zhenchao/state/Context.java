package org.zhenchao.state;

/**
 * @author zhenchao.wang 2019-11-27 19:03
 * @version 1.0.0
 */
public class Context {

    private State state;

    /**
     * 用户感兴趣的方法
     *
     * @param params
     */
    public void request(String params) {
        // 调用 State 的方法处理
        state.handle(params);
    }

    public Context setState(State state) {
        this.state = state;
        return this;
    }

}
