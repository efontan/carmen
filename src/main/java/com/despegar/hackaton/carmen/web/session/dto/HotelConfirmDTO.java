package com.despegar.hackaton.carmen.web.session.dto;

import com.despegar.hackaton.carmen.domain.model.game.Clue;

public class HotelConfirmDTO {

    private Boolean isCarmenHere;
    private Clue clue;

    public HotelConfirmDTO() {
    }

    public HotelConfirmDTO(Boolean isCarmenHere, Clue clue) {
        this.isCarmenHere = isCarmenHere;
        this.clue = clue;
    }

    public Boolean getIsCarmenHere() {
        return this.isCarmenHere;
    }

    public void setIsCarmenHere(Boolean isCarmenHere) {
        this.isCarmenHere = isCarmenHere;
    }

    public Clue getClue() {
        return this.clue;
    }

    public void setClue(Clue clue) {
        this.clue = clue;
    }

}
