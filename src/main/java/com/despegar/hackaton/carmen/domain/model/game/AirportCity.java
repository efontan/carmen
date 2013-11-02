package com.despegar.hackaton.carmen.domain.model.game;


/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class AirportCity extends City {

    private String translatedName;

    private String code;

    public AirportCity() {};

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
