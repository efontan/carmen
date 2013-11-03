package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.math.BigDecimal;

public class ApiHotelBestPrice {
	private BigDecimal avgPriceWithoutTax;

	public BigDecimal getAvgPriceWithoutTax() {
		return avgPriceWithoutTax;
	}

	public void setAvgPriceWithoutTax(BigDecimal avgPriceWithoutTax) {
		this.avgPriceWithoutTax = avgPriceWithoutTax;
	}
}
