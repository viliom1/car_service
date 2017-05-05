package com.app.models.binding;

/**
 * Created by vilimir on 04.05.17.
 */
public class UserVoteBind {
    private long id;
    private boolean vote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
