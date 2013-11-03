package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.util.List;

public class ApiHotelBestPricesData {
	private List<ApiHotelBestPrice> prices;

	public List<ApiHotelBestPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ApiHotelBestPrice> prices) {
		this.prices = prices;
	}
}
