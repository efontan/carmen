package com.despegar.hackaton.carmen.domain.client.geo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
import com.despegar.hackaton.carmen.domain.mapper.api.ApiCityMapper;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiAirportDetails;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiCities;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiHotelByCity;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiHotelsByCity;
import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component("cities.rest.client")
public class CitiesRestClient extends AbstractRestClient {

	@Autowired
	@Qualifier("cities.rest.connector")
	private RestConnector restConnector;

	private ApiCityMapper apiCityMapper = new ApiCityMapper();

	public List<Long> getHotelIdsByCity(String cityCode) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("pointtypes", "H");
		params.put("maxresults", "3");
		ApiHotelsByCity results = this.doGetWithParams("/cities",
				new TypeReference<ApiHotelsByCity>() {
				}, params, cityCode, "pointsofinterest");
		List<Long> hotelIds = Lists.newArrayList();
		for (ApiHotelByCity hotel : results.getData()) {
			hotelIds.add(hotel.getInternalId());
		}
		return hotelIds;
	}

	public String getCityCodeByAirportId(String airportId) {
		ApiAirportDetails result = this.doGet("/airports",
				new TypeReference<ApiAirportDetails>() {
				}, airportId);
		return result.getAirports().get(0).getParentCity();
	}

	public City getCityData(String cityCode) {
		ApiCities apiCities = this.doGet("/cities",
				new TypeReference<ApiCities>() {
				}, cityCode);
		return this.apiCityMapper.map(apiCities.getCities().get(0));
	}

	@Override
	public RestConnector getRestConnector() {
		return this.restConnector;
	}
}
