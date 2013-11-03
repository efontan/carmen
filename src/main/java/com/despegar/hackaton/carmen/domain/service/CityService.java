package com.despegar.hackaton.carmen.domain.service;

import com.despegar.hackaton.carmen.domain.model.game.Coordinates;

public interface CityService {
	Coordinates getGeoLocation(String cityCode);
}
