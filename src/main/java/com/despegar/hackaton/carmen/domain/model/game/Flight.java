package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

public class Flight {

	private String from;
	private String to;
	private String fromCityName;
	private String toCityName;
	private Integer stops;
	private Integer durationHours;
	private String departureDate;
	private BigDecimal price;
	private String searchUrl;

	public Flight() {
	}

	public Flight(String from, String to, String fromCityName,
			String toCityName, Integer stops, Integer durationHours,
			String departureDate, BigDecimal price, String searchUrl) {
		this.from = from;
		this.to = to;
		this.stops = stops;
		this.durationHours = durationHours;
		this.departureDate = departureDate;
		this.price = price;
		this.searchUrl = searchUrl;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Integer getStops() {
		return this.stops;
	}

	public void setStops(Integer stops) {
		this.stops = stops;
	}

	public Integer getDurationHours() {
		return this.durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public String getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSearchUrl() {
		return this.searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getFromCityName() {
		return this.fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getToCityName() {
		return this.toCityName;
	}

	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}

}
