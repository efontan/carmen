package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.util.List;

import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlight;

public class FlightDetails {

	private List<ApiFlight> flights;

	public List<ApiFlight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<ApiFlight> flights) {
		this.flights = flights;
	}

}
