package org.zhenchao.state.grind;

/**
 * @author zhenchao.wang 2019-11-28 14:15
 * @version 1.0.0
 */
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String voter, VoteManager voteManager) {
        System.out.println("请勿重复投票！");
    }
}
