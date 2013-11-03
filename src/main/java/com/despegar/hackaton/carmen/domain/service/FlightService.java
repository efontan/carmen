package com.despegar.hackaton.carmen.domain.service;

import java.util.List;

import com.despegar.hackaton.carmen.domain.model.game.Flight;

public interface FlightService {

	public Flight getFlight(String searchHash, String itineraryId);

	public List<Flight> getFlights(String from, String to);

}
