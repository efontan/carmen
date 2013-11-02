package com.despegar.hackaton.carmen.domain.model.game.response;

import com.despegar.hackaton.carmen.domain.model.game.Clue;
import com.despegar.hackaton.carmen.domain.model.game.Status;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class ClueResponse {

    private Clue clue;

    private Status status;

    public ClueResponse(Clue clue, Status status) {
        this.clue = clue;
        this.status = status;
    }

    public Clue getClue() {
        return clue;
    }

    public void setClue(Clue clue) {
        this.clue = clue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
