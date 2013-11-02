package com.despegar.hackaton.carmen.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.model.game.BaseMapCities;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.web.session.GameSession;
import com.google.common.collect.Lists;

@Service
public class GameServiceImpl implements GameService {

	private static final int INITIAL_MONEY = 50000;
	private static final int WEEKS_TO_PLAY = 1;
	private static final int GAME_FLOWS = 3;
	private static final String INITIAL_CITY_CODE = "BUE";

	@Resource
	@Qualifier("citiesMap")
	private Map<String, String> citiesMap;

	@Override
	public GameSession createGameSession(Player player) {
		Integer gameWalkthrough = RandomUtils.nextInt(GAME_FLOWS) + 1;
		String actualCityCode = INITIAL_CITY_CODE;

		DateTime actualDate = DateTime.now();
		DateTime limitDate = actualDate.plusWeeks(WEEKS_TO_PLAY);
		BigDecimal remainingMoney = new BigDecimal(INITIAL_MONEY);
		Status status = new Status(actualDate, limitDate, remainingMoney);

		return new GameSession(gameWalkthrough, actualCityCode, player, status);
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
