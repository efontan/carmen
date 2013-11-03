package com.despegar.hackaton.carmen.domain.service.impl;

import com.despegar.hackaton.carmen.domain.client.flight.FlightRestClient;
import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.despegar.hackaton.carmen.domain.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    @Qualifier("flight.rest.client")
    private FlightRestClient flightRestClient;

    @Override
    public Flight getFlight(String searchHash, String itineraryId) {
        return flightRestClient.getFlight(searchHash, itineraryId);
    }
}
