package com.despegar.hackaton.carmen.domain.service.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.despegar.hackaton.carmen.domain.model.game.*;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.web.session.GameSession;

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
	private HotelServiceImpl hotelServiceImpl;

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
		List<Hotel> cityHotels = this.getHotelServiceImpl().getCityHotels(cityCode);
		return new City(cityCode,this.citiesMap.get(cityCode),null,cityHotels);
	}

    @Override
    public List<String> getDestinations(String cityCode, int walkthrough) {
        GraphNode node = (GraphNode) applicationContext.getBean(walkthrough + UNDER + cityCode);
        List<String> cityCodes = new LinkedList<String>();
        for (GraphNode currentNode : node.getDestinations()) {
            cityCodes.add(currentNode.getCurrentCity().getCode());
        }
        return cityCodes;
    }

    public HotelServiceImpl getHotelServiceImpl() {
		return this.hotelServiceImpl;
	}

	public void setHotelServiceImpl(HotelServiceImpl hotelServiceImpl) {
		this.hotelServiceImpl = hotelServiceImpl;
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
