package com.despegar.hackaton.carmen.domain.service;

import com.despegar.hackaton.carmen.domain.model.game.Flight;

public interface FlightService {

    public Flight getFlight(String searchHash, String itineraryId);

}
