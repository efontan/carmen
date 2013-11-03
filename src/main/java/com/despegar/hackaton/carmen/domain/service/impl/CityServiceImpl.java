package com.despegar.hackaton.carmen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.client.geo.CitiesRestClient;
import com.despegar.hackaton.carmen.domain.client.hotel.HotelRestClient;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Hotel;
import com.despegar.hackaton.carmen.domain.service.CityService;

@Service("city.service")
public class CityServiceImpl implements CityService{

	@Autowired
	@Qualifier("hotel.rest.client")
	private HotelRestClient hotelRestClient;

	@Autowired
	@Qualifier("cities.rest.client")
	private CitiesRestClient citiesRestClient;
	
	@Override
	public City getCityData(String cityCode) {
		City city = this.citiesRestClient.getCityData(cityCode);
		List<Long> cityHotelIds = this.citiesRestClient.getHotelIdsByCity(cityCode);
		List<Hotel> cityHotels = this.hotelRestClient.getHotelsDetail(cityHotelIds);
		city.setHotels(cityHotels);
		return city;
	}

}
