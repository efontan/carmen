package com.despegar.hackaton.carmen.domain.model.game;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Status {
    private long remainingHours;
    private long remainingMoney;

    public Status() {
    };

    public long getRemainingHours() {
        return this.remainingHours;
    }

    public void setRemainingHours(long remainingHours) {
        this.remainingHours = remainingHours;
    }

    public long getRemainingMoney() {
        return this.remainingMoney;
    }

    public void setRemainingMoney(long remainingMoney) {
        this.remainingMoney = remainingMoney;
    }

}
