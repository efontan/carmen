package com.despegar.hackaton.carmen.web.session;

import java.io.Serializable;

import com.despegar.hackaton.carmen.domain.model.game.ExpensesDetail;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;

public class GameSession implements Serializable {

	private static final long serialVersionUID = 5338277116116504618L;
	private Integer gameWalkthrough;
	private String actualCityCode;
	private Integer actualClue;
	private Player player;
	private Status status;
	private ExpensesDetail expensesDetail;

	public GameSession(Integer gameWalkthrough, String actualCityCode,
			Player player, Status status, Integer actualClue) {
		this.gameWalkthrough = gameWalkthrough;
		this.actualCityCode = actualCityCode;
		this.player = player;
		this.status = status;
		this.actualClue = actualClue;
		this.expensesDetail = new ExpensesDetail();
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

	public Integer getActualClue() {
		return this.actualClue;
	}

	public void setActualClue(Integer actualClue) {
		this.actualClue = actualClue;
	}

	public ExpensesDetail getExpensesDetail() {
		return this.expensesDetail;
	}

	public void setExpensesDetail(ExpensesDetail expensesDetail) {
		this.expensesDetail = expensesDetail;
	}
}
