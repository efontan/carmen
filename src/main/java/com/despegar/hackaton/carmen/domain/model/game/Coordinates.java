package com.despegar.hackaton.carmen.domain.model.game;


/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Coordinates {

    private Long latitude = 0L;

    private Long longitude = 0L;

    public Coordinates(final Long latitude, final Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
