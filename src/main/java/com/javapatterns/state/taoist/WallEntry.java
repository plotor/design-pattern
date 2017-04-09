package com.javapatterns.state.taoist;

public class WallEntry {
    /**
     * @link aggregation
     * @label Current State
     */
    private WallStateIF state;

    public void setState(WallStateIF state) {
        this.state = state;
    }

    public void pass() throws WallEntryException {
        state.pass(this);
    }

    public void spell(String spell) throws WallEntryException {
        state.spell(this, spell);
    }
}
