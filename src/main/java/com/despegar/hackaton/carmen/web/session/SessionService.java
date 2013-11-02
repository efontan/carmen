package com.despegar.hackaton.carmen.web.session;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.despegar.hackaton.carmen.domain.model.game.Player;

public interface SessionService {

	Boolean addGameSessionToSessions(HttpServletRequest request,
			HttpServletResponse response, String token, GameSession gameSession);

	Boolean clearSession(HttpServletRequest request,
			HttpServletResponse response);

	Boolean createSession(HttpServletRequest request,
			HttpServletResponse response, GameSession gameSession);

	Boolean createSession(HttpServletRequest request,
			HttpServletResponse response, Integer gameWalkthrough,
			Long actualCityOid, DateTime actualDate, BigDecimal amoutRemaining,
			BigDecimal remainingHours, Player player);

	GameSession getGameSessionByToken(HttpServletRequest request, String token);

	Session getSession(HttpServletRequest request);

}
