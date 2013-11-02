package com.despegar.hackaton.carmen.domain.model.game;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public enum CharacterJob {
    MAID("Mucama"),
    JANITOR("Conserje"),
    RECEPTIONIST("Recepcionista"),
    BELLBOY("Botones"),
    WAITER("Camarero"),
    ATTENDANT("Encargado"),
    BARMAN("Barman");

    private String value;

    private CharacterJob(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
