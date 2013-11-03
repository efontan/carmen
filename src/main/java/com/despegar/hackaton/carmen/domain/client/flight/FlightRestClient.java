package com.despegar.hackaton.carmen.domain.client.flight;

import com.despegar.hackaton.carmen.domain.client.AbstractRestClient;
import com.despegar.hackaton.carmen.domain.mapper.api.ApiFlightMapper;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlight;
import com.despegar.hackaton.carmen.domain.model.api.hotel.FlightDetails;
import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.utils.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("flight.rest.client")
public class FlightRestClient extends AbstractRestClient {

	private static final String AVAILABILITY_ONEWAY = "/availability/flights/oneWay";
    private static final String GET_PREVIOUS_SEARCHED_FLIGHT = "/availability/flights/itineraries";

	private ApiFlightMapper apiFlightMapper = new ApiFlightMapper();

	@Autowired
	@Qualifier("flight.rest.connector")
	private RestConnector flightRestConnector;

	public List<Flight> getFlights(String from, String to, String departureDate) {
		List<Flight> result = Lists.newLinkedList();
		Integer adults = 1;
		Integer children = 0;
		Integer infants = 0;
		Map<String, Object> params = Maps.newHashMap();
		params.put("page", 1);
		params.put("pagesize", 3);

		FlightDetails flightDetails = this.doGetWithParams(AVAILABILITY_ONEWAY,
				new TypeReference<FlightDetails>() {
				}, params, from, to, departureDate, adults, children, infants);

		for (ApiFlight apiFlight : flightDetails.getFlights()) {
			result.add(this.apiFlightMapper.map(apiFlight));
		}

		return result;
	}

    public Flight getFlight(final String searchHash, final String itineraryId) {
        /**TODO;taitooz waiting emma mapper.**/
        Object object = doGet(GET_PREVIOUS_SEARCHED_FLIGHT, new TypeReference<Object>() {}, searchHash, itineraryId);
        return (Flight) object;
    }

	@Override
	public RestConnector getRestConnector() {
		return this.flightRestConnector;
	}

}
