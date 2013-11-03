package com.despegar.hackaton.carmen.domain.model.api.geo;

import java.util.List;

public class ApiHotelsByCity {
	List<ApiHotelByCity> data;

	public List<ApiHotelByCity> getData() {
		return data;
	}

	public void setData(List<ApiHotelByCity> data) {
		this.data = data;
	}
}
