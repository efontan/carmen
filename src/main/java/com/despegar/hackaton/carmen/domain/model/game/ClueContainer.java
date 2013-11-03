package com.despegar.hackaton.carmen.domain.model.game;

import java.util.List;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class ClueContainer {

    private List<Clue> clues;

    public ClueContainer() {}

    public ClueContainer(final List<Clue> theClues) {
        clues = theClues;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public Clue getClueByIndex(Integer clueIndex) {
        return clues.get(clueIndex);
    }
}
