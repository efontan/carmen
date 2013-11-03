package com.despegar.hackaton.carmen.domain.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;
import com.despegar.hackaton.carmen.domain.service.CityService;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.domain.service.HotelService;
import com.despegar.hackaton.carmen.web.session.GameSession;

@Service
public class GameServiceImpl implements GameService {

  private static final int INITIAL_MONEY = 50000;
  private static final int WEEKS_TO_PLAY = 1;
  private static final int GAME_FLOWS = 1;
  private static final String INITIAL_CITY_CODE = "BUE";
    private static final int INITIAL_CLUE = 0;

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CityService cityService;

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
    return new GameSession(gameWalkthrough, actualCityCode, player, status, INITIAL_CLUE);
	}

	@Override
	public City getCityData(String cityCode) {
		City city = this.cityService.getCityData(cityCode);
		return city;
	}

	public HotelService getHotelService() {
		return this.hotelService;
	}

	public void setHotelServiceImpl(HotelService hotelService) {
		this.hotelService = hotelService;
	}

}
