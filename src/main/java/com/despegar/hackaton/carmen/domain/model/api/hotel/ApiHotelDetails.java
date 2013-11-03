package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.util.List;

public class ApiHotelDetails {
	private List<ApiHotel> hotels;

	public List<ApiHotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<ApiHotel> hotels) {
		this.hotels = hotels;
	}
}
