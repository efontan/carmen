package com.despegar.hackaton.carmen.web.session;

import java.io.Serializable;

import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;

public class GameSession implements Serializable {

	private static final long serialVersionUID = 5338277116116504618L;
	private Integer gameWalkthrough;
	private String actualCityCode;
	private Player player;
	private Status status;

	public GameSession(Integer gameWalkthrough, String actualCityCode,
			Player player, Status status) {
		this.gameWalkthrough = gameWalkthrough;
		this.actualCityCode = actualCityCode;
		this.player = player;
		this.status = status;
	}

	public Integer getGameWalkthrough() {
		return this.gameWalkthrough;
	}

	public void setGameWalkthrough(Integer gameWalkthrough) {
		this.gameWalkthrough = gameWalkthrough;
	}

	public String getActualCityCode() {
		return this.actualCityCode;
	}

	public void setActualCityCode(String actualCityCode) {
		this.actualCityCode = actualCityCode;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
