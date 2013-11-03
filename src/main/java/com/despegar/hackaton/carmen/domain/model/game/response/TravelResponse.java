package com.despegar.hackaton.carmen.domain.model.game.response;

import com.despegar.hackaton.carmen.domain.model.game.AirportCity;
import com.despegar.hackaton.carmen.domain.model.game.Status;

import java.util.List;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class TravelResponse {

    private AirportCity currentCity;

    private List<AirportCity> destinations;

    private Status status;

    public TravelResponse(AirportCity currentCity, List<AirportCity> destinations, Status status) {
        this.currentCity = currentCity;
        this.destinations = destinations;
        this.status = status;
    }

    public AirportCity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(AirportCity currentCity) {
        this.currentCity = currentCity;
    }

    public List<AirportCity> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<AirportCity> destinations) {
        this.destinations = destinations;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
