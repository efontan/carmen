package com.despegar.hackaton.carmen.web.session.dto;

import java.util.List;

import com.despegar.hackaton.carmen.domain.model.Hotel;
import com.despegar.hackaton.carmen.domain.model.game.GraphNode;
import com.despegar.hackaton.carmen.domain.model.game.Status;

public class TravelResponseDTO {

    private Status status;
    private List<Hotel> hotels;
    private GraphNode GraphNode;

    public TravelResponseDTO() {
    }

    public TravelResponseDTO(Status status, List<Hotel> hotels, GraphNode graphNode) {
        this.status = status;
        this.hotels = hotels;
        this.GraphNode = graphNode;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Hotel> getHotels() {
        return this.hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public GraphNode getGraphNode() {
        return this.GraphNode;
    }

    public void setGraphNode(GraphNode graphNode) {
        this.GraphNode = graphNode;
    }

}
