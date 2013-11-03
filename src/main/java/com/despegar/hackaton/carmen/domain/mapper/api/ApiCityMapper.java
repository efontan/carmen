package com.despegar.hackaton.carmen.domain.mapper.api;

import com.despegar.hackaton.carmen.domain.mapper.Mapper;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiCity;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Coordinates;

public class ApiCityMapper implements Mapper<ApiCity, City> {

	@Override
	public City map(ApiCity apiCity) {
		City city = new City();
		city.setCode(apiCity.getId());
		city.setName(apiCity.getName());
		city.setPosition(new Coordinates(apiCity.getGeoLocation().getLatitude(), apiCity.getGeoLocation().getLongitude()));
		return city;
	}

}
