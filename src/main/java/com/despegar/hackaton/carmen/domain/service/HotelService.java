package com.despegar.hackaton.carmen.domain.service;

import java.math.BigDecimal;
import java.util.List;

import com.despegar.hackaton.carmen.domain.model.game.Hotel;

public interface HotelService {

	List<Hotel> getCityHotels(String cityCodes);

    BigDecimal getPrice(long id);
    
    String getName(long id);
}
