package com.despegar.hackaton.carmen.domain.model.game;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Clue {

    private CharacterJob characterJob;

    private String name;

    private String description;

    private boolean inTheHouse = false;

    public Clue() {};

    public Clue(final CharacterJob characterJob, final String description) {
        this.characterJob = characterJob;
        this.description = description;
    }

    public Clue(final CharacterJob characterJob, final String name, final String description, final Boolean isInTheHouse) {
        this(characterJob, description);
        this.name = name;
        this.inTheHouse = isInTheHouse;
    }

    public CharacterJob getCharacterJob() {
        return characterJob;
    }

    public void setCharacterJob(CharacterJob characterJob) {
        this.characterJob = characterJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInTheHouse() {
        return inTheHouse;
    }

    public void setInTheHouse(boolean inTheHouse) {
        this.inTheHouse = inTheHouse;
    }
}
