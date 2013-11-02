package com.despegar.hackaton.carmen.domain.model.api.hotel;

import java.math.BigDecimal;

public class ApiHotelReviewSummary {
    private BigDecimal overallRating;

    public BigDecimal getOverallRating() {
        return this.overallRating;
    }

    public void setOverallRating(BigDecimal overallRating) {
        this.overallRating = overallRating;
    }
}
