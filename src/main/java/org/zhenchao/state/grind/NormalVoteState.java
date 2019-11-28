package org.zhenchao.state.grind;

/**
 * @author zhenchao.wang 2019-11-28 09:27
 * @version 1.0.0
 */
public class NormalVoteState implements VoteState {

    @Override
    public void vote(String voter, VoteManager voteManager) {
        voteManager.getBallotBox().put(voter, 1);
        System.out.println("投票成功！");
    }
}
