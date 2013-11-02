package com.despegar.hackaton.carmen.domain.client.geo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiHotelByCity;
import com.despegar.hackaton.carmen.domain.model.api.geo.ApiHotelsByCity;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component("cities.rest.client")
public class CitiesRestClient extends AbstractRestClient {

	@Autowired
	@Qualifier("cities.rest.connector")
	private RestConnector restConnector;

	public List<Long> getHotelIdsByCity(String cityCode) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("pointtypes", "H");
		params.put("maxresults", "3");
		ApiHotelsByCity results = this.doGetWithParams("/cities",
				new TypeReference<ApiHotelsByCity>() {
				}, params,cityCode,"pointsofinterest");
		List<Long> hotelIds = Lists.newArrayList();
		for (ApiHotelByCity hotel : results.getData()) {
			hotelIds.add(hotel.getInternalId());
		}
		return hotelIds;
	}

	@Override
	public RestConnector getRestConnector() {
		return this.restConnector;
	}
}
