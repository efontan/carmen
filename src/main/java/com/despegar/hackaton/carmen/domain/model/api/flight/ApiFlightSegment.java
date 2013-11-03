package com.despegar.hackaton.carmen.domain.model.api.flight;

public class ApiFlightSegment {

	private ApiFlightSegmentDetails departure;
	private ApiFlightSegmentDetails arrival;
	private String duration;

	public ApiFlightSegmentDetails getDeparture() {
		return this.departure;
	}

	public void setDeparture(ApiFlightSegmentDetails departure) {
		this.departure = departure;
	}

	public ApiFlightSegmentDetails getArrival() {
		return this.arrival;
	}

	public void setArrival(ApiFlightSegmentDetails arrival) {
		this.arrival = arrival;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
