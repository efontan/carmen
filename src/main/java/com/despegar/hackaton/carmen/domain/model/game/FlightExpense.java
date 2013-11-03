package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.math.BigDecimal;

public class FlightExpense implements Serializable {

	private static final long serialVersionUID = 8725813730253090752L;
	private String fromCityName;
	private String toCityName;
	private BigDecimal price;

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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
