package org.zhenchao.state.grind;

/**
 * @author zhenchao.wang 2019-11-27 19:06
 * @version 1.0.0
 */
public interface VoteState {

    /**
     * 处理状态对应的行为
     *
     * @param voter 投票人
     * @param voteManager 投票上下文
     */
    void vote(String voter, VoteManager voteManager);

}
