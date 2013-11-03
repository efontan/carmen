package com.despegar.hackaton.carmen.domain.service;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.web.session.GameSession;

import java.util.List;

public interface GameService {

	public GameSession createGameSession(Player player);

	public City getCityData(String cityCode);

    public List<String> getDestinations(String cityCode, int walkthrough);
}
