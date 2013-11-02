package com.despegar.hackaton.carmen.web.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

import com.despegar.hackaton.carmen.domain.exception.InvalidAccessException;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.google.common.collect.Lists;

public class Session
    implements Serializable {

    private String sessionId;

    private static final long serialVersionUID = 1L;
    private Map<String, GameSession> gameSessions = new HashMap<String, GameSession>();

    public Session() {
        this.sessionId = UUID.randomUUID().toString();
    }

    public void addGameSession(String token, Integer gameWalkthrough, Long actualCityOid, DateTime actualDate,
        BigDecimal amoutRemaining, BigDecimal remainingHours, Player player) {
        GameSession gameSession = new GameSession(gameWalkthrough, actualCityOid, actualDate, amoutRemaining,
            remainingHours, player);
        this.gameSessions.put(token, gameSession);
    }

    public void addGameSession(String token, GameSession gameSession) {
        this.gameSessions.put(token, gameSession);
    }

    public GameSession getGameSessionByToken(String token) {
        GameSession gameSession = this.gameSessions.get(token);
        if (gameSession == null) {
            throw new InvalidAccessException("Invalida Access token: " + token + ". GameSession Not found in Session.");
        }
        return gameSession;
    }

    public String getName() {
        return null;
    }

    public String getEmail() {
        return this.getName();
    }

    public List<String> getEmails() {
        return Lists.newArrayList();
    }

    public Map<String, GameSession> getGameSessions() {
        return this.gameSessions;
    }

    public void setGameSessions(Map<String, GameSession> gameSessions) {
        this.gameSessions = gameSessions;
    }

    public Boolean isValid() {
        return !this.getGameSessions().keySet().isEmpty();
    }

    public Boolean isOperatorSession() {
        return Boolean.FALSE;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean isSocialLogin() {
        return Boolean.FALSE;
    }


}
