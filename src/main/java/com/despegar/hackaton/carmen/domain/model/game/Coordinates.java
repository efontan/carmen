package com.despegar.hackaton.carmen.domain.model.game;


/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Coordinates {

    private double latitude = 0L;

    private double longitude = 0L;

    public Coordinates(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
