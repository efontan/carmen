package com.despegar.hackaton.carmen.domain.model.game.response;

import com.despegar.hackaton.carmen.domain.model.game.GraphNode;
import com.despegar.hackaton.carmen.domain.model.game.Status;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class TravelResponse {

    private GraphNode node;

    private Status status;

    public TravelResponse(GraphNode node, Status status) {
        this.node = node;
        this.status = status;
    }

    public GraphNode getNode() {
        return node;
    }

    public void setNode(GraphNode node) {
        this.node = node;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
