package com.despegar.hackaton.carmen.web.session;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;

public class GameSession
    implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gameWalkthrough;
    private Long actualCityOid;
    private DateTime actualDate;
    private BigDecimal remainingAmount;
    private BigDecimal remainingHours;
    private Player player;
    private Status status = new Status();


    public GameSession() {
    }

    public GameSession(Integer gameWalkthrough, Long actualCityOid, DateTime actualDate, BigDecimal remainingAmount,
        BigDecimal remainingHours, Player player) {
        this.gameWalkthrough = gameWalkthrough;
        this.actualCityOid = actualCityOid;
        this.actualDate = actualDate;
        this.remainingAmount = remainingAmount;
        this.remainingHours = remainingHours;
        this.player = player;
    }

    public Integer getGameWalkthrough() {
        return this.gameWalkthrough;
    }

    public void setGameWalkthrough(Integer gameWalkthrough) {
        this.gameWalkthrough = gameWalkthrough;
    }

    public Long getActualCityOid() {
        return this.actualCityOid;
    }

    public void setActualCityOid(Long actualCityOid) {
        this.actualCityOid = actualCityOid;
    }

    public DateTime getActualDate() {
        return this.actualDate;
    }

    public void setActualDate(DateTime actualDate) {
        this.actualDate = actualDate;
    }

    public BigDecimal getRemainingAmout() {
        return this.remainingAmount;
    }

    public void setRemainingAmout(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BigDecimal getRemainingHours() {
        return this.remainingHours;
    }

    public void setRemainingHours(BigDecimal remainingHours) {
        this.remainingHours = remainingHours;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Status getStatus() {
        this.status.setRemainingMoney(this.getRemainingAmout());
        this.status.setRemainingHours(this.getRemainingHours());;
        return this.status;
    }

}
