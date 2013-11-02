package com.despegar.hackaton.carmen.domain.service;

import java.util.List;

import com.despegar.hackaton.carmen.domain.model.game.Hotel;

public interface HotelService {

	List<Hotel> getCityHotels(String cityCodes);
}
