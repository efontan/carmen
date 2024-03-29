package com.despegar.hackaton.carmen.domain.service;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.web.session.GameSession;

import java.util.List;

public interface GameService {

	public GameSession createGameSession(Player player);

    public GameSession restartSession(GameSession session);

	public City getCityData(String cityCode, int walkthrough);

    public List<String> getDestinations(String cityCode, int walkthrough);
}
