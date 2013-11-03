package com.despegar.hackaton.carmen.domain.mapper.api;

import com.despegar.hackaton.carmen.domain.mapper.Mapper;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlight;
import com.despegar.hackaton.carmen.domain.model.game.Flight;

public class ApiFlightMapper implements Mapper<ApiFlight, Flight> {

	@Override
	public Flight map(ApiFlight apiFlight) {
		Flight flight = new Flight();

		apiFlight.getOutboundRoutes();

		flight.setDepartureDate(null);
		flight.setDurationHours(null);
		flight.setFrom(null);
		flight.setTo(null);
		flight.setStops(null);
		flight.setPrice(null);
		flight.setSearchUrl(null);

		return flight;
	}

}
