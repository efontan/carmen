package com.despegar.hackaton.carmen.domain.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.model.game.BaseMapCities;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.session.GameSession;
import com.google.common.collect.Lists;

@Service
public class GameServiceImpl implements GameService {

	@Resource
	@Qualifier("citiesMap")
	private Map<String, String> citiesMap;

	@Override
	public GameSession createGameSession(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseMapCities getBaseMapCities() {
		BaseMapCities baseMapCities = new BaseMapCities();
		List<City> cities = Lists.newLinkedList();

		for (Map.Entry<String, String> entry : this.citiesMap.entrySet()) {
			String cityCode = entry.getKey();
			String cityName = entry.getValue();
			City city = new City(cityCode, cityName, null, null);
			cities.add(city);
		}

		baseMapCities.setCities(cities);

		return baseMapCities;
	}

}
