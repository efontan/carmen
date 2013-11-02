package com.despegar.hackaton.carmen.domain.model.game;

import com.despegar.hackaton.carmen.domain.model.City;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class AirportCity extends City {

    private String translatedName;

    public AirportCity() {};

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }
}
