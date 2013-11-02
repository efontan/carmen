package com.despegar.hackaton.carmen.domain.model.game;

import java.util.List;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class GraphNode {

    private AirportCity currentCity;

    private List<AirportCity> destinations;

    public GraphNode(){};

    public GraphNode(final AirportCity city, final List<AirportCity> nextDestinations) {
        currentCity = city;
        destinations = nextDestinations;
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
}
