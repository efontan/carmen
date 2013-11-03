package com.despegar.hackaton.carmen.domain.client.hotel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
import com.despegar.hackaton.carmen.domain.mapper.api.ApiHotelMapper;
import com.despegar.hackaton.carmen.domain.model.api.hotel.ApiHotel;
import com.despegar.hackaton.carmen.domain.model.api.hotel.ApiHotelBestPrices;
import com.despegar.hackaton.carmen.domain.model.api.hotel.ApiHotelDetails;
import com.despegar.hackaton.carmen.domain.model.game.Hotel;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component("hotel.rest.client")
public class HotelRestClient extends AbstractRestClient {

	private static final String HOTELS_DETAIL = "/hotels";
	private static final String HOTELS_BEST_PRICES = "/availability/hotels";
	@Autowired
	@Qualifier("hotel.rest.connector")
	private RestConnector hotelRestConnector;

	private ApiHotelMapper apiHotelMapper = new ApiHotelMapper();

	public List<Hotel> getHotelsDetail(List<Long> hotelIds) {
		String hotelIdsAsString = Joiner.on(",").join(hotelIds);
		List<Hotel> result = Lists.newArrayList();
		ApiHotelDetails hotelsDetail = this.doGet(HOTELS_DETAIL,
				new TypeReference<ApiHotelDetails>() {
				}, hotelIdsAsString);
		for (ApiHotel apiHotel : hotelsDetail.getHotels()) {
			Hotel hotel = this.apiHotelMapper.map(apiHotel);
			hotel.setPrice(this.getPrice(hotel.getId()));
			result.add(hotel);
		}
		return result;
	}

	public BigDecimal getPrice(long id) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("currency", "ARS");
		ApiHotelBestPrices prices = this.doGetWithParams(HOTELS_BEST_PRICES,
				new TypeReference<ApiHotelBestPrices>() {
				}, params, id, "bestprices");
		BigDecimal bestPrice = new BigDecimal(1000);

		if (!prices.getData().getPrices().isEmpty()
				&& prices.getData().getPrices().get(0).getAvgPriceWithoutTax() != null) {
			bestPrice = prices.getData().getPrices().get(0)
					.getAvgPriceWithoutTax();
		}
		return bestPrice;
	}

	@Override
	public RestConnector getRestConnector() {
		return this.hotelRestConnector;
	}
}