package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Status {
    private BigDecimal remainingHours;
    private BigDecimal remainingMoney;

    public Status() {
    };

    public BigDecimal getRemainingHours() {
        return this.remainingHours;
    }

    public void setRemainingHours(BigDecimal remainingHours) {
        this.remainingHours = remainingHours;
    }

    public BigDecimal getRemainingMoney() {
        return this.remainingMoney;
    }

    public void setRemainingMoney(BigDecimal remainingMoney) {
        this.remainingMoney = remainingMoney;
    }

}
