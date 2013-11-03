package com.despegar.hackaton.carmen.domain.model.api.flight;

public class ApiFlightSegment {

	private ApiFlightDeparture departure;
	private ApiFlightDeparture arrival;

	public ApiFlightDeparture getDeparture() {
		return this.departure;
	}

	public void setDeparture(ApiFlightDeparture departure) {
		this.departure = departure;
	}

	public ApiFlightDeparture getArrival() {
		return this.arrival;
	}

	public void setArrival(ApiFlightDeparture arrival) {
		this.arrival = arrival;
	}

}
