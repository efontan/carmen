package com.despegar.hackaton.carmen.domain.client.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
import com.despegar.hackaton.carmen.domain.mapper.api.ApiHotelMapper;
import com.despegar.hackaton.carmen.domain.model.api.hotel.ApiHotel;
import com.despegar.hackaton.carmen.domain.model.game.Hotel;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Component("hotel.rest.client")
public class HotelRestClient
    extends AbstractRestClient {

    private static final String HOTELS_DETAIL = "/detail";
    @Autowired
    @Qualifier("hotel.rest.connector")
    private RestConnector hotelRestConnector;

    private ApiHotelMapper apiHotelMapper = new ApiHotelMapper();

    public List<Hotel> getHotelsDetail(List<Long> hotelIds) {
        String hotelIdsAsString = Joiner.on(",").join(hotelIds);
        List<Hotel> result = Lists.newArrayList();
        HotelsDetail hotelsDetail = this.doGet(HOTELS_DETAIL, new TypeReference<HotelsDetail>() {}, hotelIdsAsString);
        for (ApiHotel apiHotel : hotelsDetail.getHotels()) {
            result.add(this.apiHotelMapper.map(apiHotel));
        }
        return result;
    }

    private final class HotelsDetail {

        private List<ApiHotel> hotels = Lists.newArrayList();

        public List<ApiHotel> getHotels() {
            return this.hotels;
        }
    }

    @Override
    public RestConnector getRestConnector() {
        return this.hotelRestConnector;
    }
}