package com.despegar.hackaton.carmen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.client.geo.CitiesRestClient;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Coordinates;
import com.despegar.hackaton.carmen.domain.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	@Qualifier("cities.rest.client")
	private CitiesRestClient citiesRestClient;
	
	@Override
	public Coordinates getGeoLocation(String cityCode) {
//		City city = this.citiesRestClient.getCity(cityCode);
//		return city.getPosition();
		return null;
	}

}
