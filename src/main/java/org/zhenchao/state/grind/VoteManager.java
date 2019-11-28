package org.zhenchao.state.grind;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenchao.wang 2019-11-27 19:06
 * @version 1.0.0
 */
public class VoteManager {

    /** 投票箱，记录 [用户名称：投票次数] */
    private Map<String, Integer> ballotBox = new HashMap<>();

    private VoteState voteState;

    // public void vote(String user, )

    public Map<String, Integer> getBallotBox() {
        return ballotBox;
    }
}
