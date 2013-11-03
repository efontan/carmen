package com.despegar.hackaton.carmen.domain.model.api.flight;

public class ApiFlightDeparture {

	private String date;
	private Long timezone;
	private String location;

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getTimezone() {
		return this.timezone;
	}

	public void setTimezone(Long timezone) {
		this.timezone = timezone;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
