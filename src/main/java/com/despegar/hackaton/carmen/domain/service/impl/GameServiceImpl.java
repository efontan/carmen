package com.despegar.hackaton.carmen.domain.service.impl;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.GraphNode;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;
import com.despegar.hackaton.carmen.domain.service.CityService;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.domain.service.HotelService;
import com.despegar.hackaton.carmen.web.session.GameSession;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService, ApplicationContextAware {

	private static final int INITIAL_MONEY = 50000;
	private static final int WEEKS_TO_PLAY = 1;
	private static final int GAME_FLOWS = 1;
	private static final String INITIAL_CITY_CODE = "BUE";
	private static final int INITIAL_CLUE = 0;
	private static final String UNDER = "_";

	private ApplicationContext applicationContext;

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
		return new GameSession(gameWalkthrough, actualCityCode, player, status,
				INITIAL_CLUE);
	}

    @Override
    public GameSession restartSession(GameSession session) {
        Integer gameWalkthrough = RandomUtils.nextInt(GAME_FLOWS) + 1;
        DateTime actualDate = DateTime.now();
        session.setGameWalkthrough(gameWalkthrough);
        session.setActualCityCode(INITIAL_CITY_CODE);
        Status newStatus = session.getStatus();
        newStatus.setActualDate(actualDate);
        newStatus.setRemainingMoney(new BigDecimal(INITIAL_MONEY));
        newStatus.setLimitDate(actualDate.plusWeeks(WEEKS_TO_PLAY));
        session.setStatus(newStatus);
        return session;
    }

    @Override
	public City getCityData(String cityCode, int walkthrough) {
		City city = this.cityService.getCityData(cityCode);
		if (walkthrough > 0) {
			city.setNextDestinations(this
					.getDestinations(cityCode, walkthrough));
		}
		return city;
	}

	@Override
	public List<String> getDestinations(String cityCode, int walkthrough) {
		GraphNode node = (GraphNode) applicationContext.getBean(walkthrough
				+ UNDER + cityCode);
		List<String> cityCodes = new LinkedList<String>();
		for (GraphNode currentNode : node.getDestinations()) {
			cityCodes.add(currentNode.getCurrentCity().getCode());
		}
		return cityCodes;
	}

	public HotelService getHotelService() {
		return this.hotelService;
	}

	public void setHotelServiceImpl(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
