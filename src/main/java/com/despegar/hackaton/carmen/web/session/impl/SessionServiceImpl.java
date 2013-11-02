package com.despegar.hackaton.carmen.web.session.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.web.session.GameSession;
import com.despegar.hackaton.carmen.web.session.Session;
import com.despegar.hackaton.carmen.web.session.SessionService;

@Service(value = "sessionService")
public class SessionServiceImpl implements SessionService {

	private static final String SESSION_KEY = "SESSION_CARMEN";

	@Override
	public Boolean createSession(HttpServletRequest request,
			HttpServletResponse response, GameSession gameSession) {
		Session session = new Session();
		Player player = gameSession.getPlayer();
		String token = player.getEmail();

		return this.addGameSessionToSessions(request, response, session, token,
				gameSession, Boolean.TRUE);
	}

	@Override
	public Boolean addGameSessionToSessions(HttpServletRequest request,
			HttpServletResponse response, String token, GameSession gameSession) {
		Session session = this.getSession(request);
		return this.addGameSessionToSessions(request, response, session, token,
				gameSession, Boolean.FALSE);
	}

	@Override
	public Session getSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(Boolean.FALSE);
		if (httpSession != null) {
			Session Session = (Session) httpSession.getAttribute(SESSION_KEY);
			return Session;
		}
		return null;
	}

	@Override
	public GameSession getGameSessionByToken(HttpServletRequest request,
			String token) {
		Session session = this.getSession(request);
		GameSession gameSession = session.getGameSessionByToken(token);
		return gameSession;
	}

	@Override
	public Boolean clearSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession httpSession = request.getSession(Boolean.FALSE);
		if (httpSession != null) {
			httpSession.invalidate();
		}
		return Boolean.TRUE;
	}

	private Boolean addGameSessionToSessions(HttpServletRequest request,
			HttpServletResponse response, Session session, String token,
			GameSession gameSession, Boolean createSession) {
		session.addGameSession(token, gameSession);
		request.getSession(createSession).setAttribute(SESSION_KEY, session);
		return Boolean.TRUE;
	}

}
