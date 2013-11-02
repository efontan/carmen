package com.despegar.hackaton.carmen.domain.model.game;

import java.util.List;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class GraphNode {

    private AirportCity currentCity;

    private List<GraphNode> destinations;

    private ClueContainer clues;

    public GraphNode(){};

    public GraphNode(final AirportCity city, final List<GraphNode> nextDestinations, final ClueContainer clueContainer) {
        currentCity = city;
        destinations = nextDestinations;
        clues = clueContainer;
    }

    public AirportCity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(AirportCity currentCity) {
        this.currentCity = currentCity;
    }

    public List<GraphNode> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<GraphNode> destinations) {
        this.destinations = destinations;
    }

    public ClueContainer getClues() {
        return clues;
    }

    public void setClues(ClueContainer clues) {
        this.clues = clues;
    }

}
