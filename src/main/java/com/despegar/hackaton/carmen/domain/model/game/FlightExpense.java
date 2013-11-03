package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

public class FlightExpense {
	private String fromCityName;
	private String toCityName;
	private BigDecimal price;

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getToCityName() {
		return toCityName;
	}

	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
