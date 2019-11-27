package org.zhenchao.state;

/**
 * @author zhenchao.wang 2019-11-27 19:01
 * @version 1.0.0
 */
public interface State {

    /**
     * 状态处理函数
     *
     * @param params 示例参数
     */
    void handle(String params);

}
