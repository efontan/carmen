package com.despegar.hackaton.carmen.domain.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.despegar.hackaton.carmen.domain.client.flight.FlightRestClient;
import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.despegar.hackaton.carmen.domain.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	@Qualifier("flight.rest.client")
	private FlightRestClient flightRestClient;

	@Override
	public Flight getFlight(String searchHash, String itineraryId) {
		return this.flightRestClient.getFlight(searchHash, itineraryId);
	}

	@Override
	public List<Flight> getFlights(String from, String to) {
		DateTime now = DateTime.now();
		return this.flightRestClient.getFlights(from, to,
				formatedDate(now, "yyyy-MM-dd"));
	}

	public static String formatedDate(DateTime dateTime, String pattern) {
		DateTimeFormatter df = DateTimeFormat.forPattern(pattern);
		return dateTime.toString(df);
	}
}
