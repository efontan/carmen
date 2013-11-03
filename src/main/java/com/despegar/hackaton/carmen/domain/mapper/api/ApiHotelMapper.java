package com.despegar.hackaton.carmen.domain.mapper.api;

import com.despegar.hackaton.carmen.domain.mapper.Mapper;
import com.despegar.hackaton.carmen.domain.model.api.hotel.ApiHotel;
import com.despegar.hackaton.carmen.domain.model.game.Coordinates;
import com.despegar.hackaton.carmen.domain.model.game.Hotel;

public class ApiHotelMapper implements Mapper<ApiHotel, Hotel> {

	private static final int MAX_DESCRIPTION_LENGTH = 200;

	@Override
	public Hotel map(ApiHotel apiHotel) {
		Hotel hotel = new Hotel();
		hotel.setId(apiHotel.getId());
		String shortDescription = this.cutDescription(apiHotel.getDescription());
		hotel.setDescription(shortDescription);
		if (!apiHotel.getPictures().isEmpty()) {
			hotel.setImageKey(apiHotel.getPictures().get(0));
		}
		hotel.setName(apiHotel.getName());
		if (apiHotel.getGeoLocation() != null)
			hotel.setPosition(new Coordinates(apiHotel.getGeoLocation()
					.getLatitude(), apiHotel.getGeoLocation().getLongitude()));
		if (apiHotel.getReviewSummary() != null)
			hotel.setRating(apiHotel.getReviewSummary().getOverallRating());
		hotel.setStarsNumber(apiHotel.getStarRating());
		return hotel;
	}

	private String cutDescription(String description) {
		String result = description;
		if(description.length()>MAX_DESCRIPTION_LENGTH){
			result = description.substring(0, MAX_DESCRIPTION_LENGTH);
			result += "...";
		}
		return result.toString();
	}

}
