package com.despegar.hackaton.carmen.domain.client.geo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
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
		params.put("maxresult", "3");
		HotelIdsResults results = this.doGetWithParams("/",
				new TypeReference<HotelIdsResults>() {
				}, params, cityCode, "pointsofinterest");
		List<Long> hotelIds = Lists.newArrayList();
		for (Hotel hotel : results.getData()) {
			hotelIds.add(hotel.getInternalId());
		}
		return hotelIds;
	}

	private class HotelIdsResults {

		private List<Hotel> data;

		public List<Hotel> getData() {
			return this.data;
		}

	}

	private class Hotel {

		private long internalId;

		public long getInternalId() {
			return this.internalId;
		}
	}

	@Override
	public RestConnector getRestConnector() {
		return this.restConnector;
	}
}
