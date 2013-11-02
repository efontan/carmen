package com.despegar.hackaton.carmen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.despegar.hackaton.carmen.domain.client.geo.CitiesRestClient;
import com.despegar.hackaton.carmen.domain.client.hotel.HotelRestClient;
import com.despegar.hackaton.carmen.domain.model.game.Hotel;
import com.despegar.hackaton.carmen.domain.service.HotelService;

public class HotelServiceImpl implements HotelService {

    @Autowired
    @Qualifier("hotel.rest.client")
    private HotelRestClient hotelRestClient;

    @Autowired
    @Qualifier("cities.rest.client")
    private CitiesRestClient citiesRestClient;

    @Override
    public List<Hotel> getCityHotels(String cityCode) {
        List<Long> hotelIds = this.citiesRestClient.getHotelIdsByCity(cityCode);
        return this.hotelRestClient.getHotelsDetail(hotelIds);
    }

}
