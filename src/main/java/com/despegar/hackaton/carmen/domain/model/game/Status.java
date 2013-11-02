package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class Status {

	private DateTime actualDate;
	private DateTime limitDate;
	private BigDecimal remainingMoney;

	public Status() {
	}

	public Status(DateTime actualDate, DateTime limitDate,
			BigDecimal remainingMoney) {
		this.actualDate = actualDate;
		this.limitDate = limitDate;
		this.remainingMoney = remainingMoney;
	}

	public DateTime getActualDate() {
		return this.actualDate;
	}

	public void setActualDate(DateTime actualDate) {
		this.actualDate = actualDate;
	}

	public DateTime getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(DateTime limitDate) {
		this.limitDate = limitDate;
	}

	public BigDecimal getRemainingMoney() {
		return this.remainingMoney;
	}

	public void setRemainingMoney(BigDecimal remainingMoney) {
		this.remainingMoney = remainingMoney;
	};

}
