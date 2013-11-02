package com.despegar.hackaton.carmen.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionService {

	Boolean addGameSessionToSessions(HttpServletRequest request,
			HttpServletResponse response, String token, GameSession gameSession);

	Boolean clearSession(HttpServletRequest request,
			HttpServletResponse response);

	Boolean createSession(HttpServletRequest request,
			HttpServletResponse response, GameSession gameSession);

	GameSession getGameSessionByToken(HttpServletRequest request, String token);

	Session getSession(HttpServletRequest request);

}
