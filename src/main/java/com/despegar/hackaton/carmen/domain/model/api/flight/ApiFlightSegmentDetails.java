package com.despegar.hackaton.carmen.domain.model.api.flight;

public class ApiFlightSegmentDetails {

	private String date;
	private Double timezone;
	private String location;

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getTimezone() {
		return this.timezone;
	}

	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
