package com.despegar.hackaton.carmen.domain.service;

import com.despegar.hackaton.carmen.domain.model.game.BaseMapCities;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.web.session.GameSession;

public interface GameService {

	public GameSession createGameSession(Player player);

	public BaseMapCities getBaseMapCities();
}
