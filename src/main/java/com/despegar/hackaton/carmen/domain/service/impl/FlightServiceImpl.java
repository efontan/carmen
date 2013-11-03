package com.despegar.hackaton.carmen.domain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
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

	@Resource
	@Qualifier("mockFlightList")
	List<Flight> mockFlightList;

	@Override
	public Flight getFlight(String searchHash, String itineraryId) {
		return this.flightRestClient.getFlight(searchHash, itineraryId);
	}

	@Override
	public List<Flight> getFlights(String from, String to) {
		DateTime departureDate = DateTime.now().plusMonths(
				FlightServiceConstants.PLUS_MONTHS);
		List<Flight> serviceFlights = this.flightRestClient.getFlights(
				from,
				to,
				formatedDate(departureDate,
						FlightServiceConstants.SEARCH_DATE_PATTER));

		serviceFlights = this.checkFlights(serviceFlights, from, to,
				departureDate);

		return serviceFlights;
	}

	private List<Flight> checkFlights(List<Flight> serviceFlights, String from,
			String to, DateTime departureDate) {
		int cantFlights = serviceFlights.size();
		if (cantFlights < 3) {
			for (int i = 0; i < 3 - cantFlights; i++) {
				serviceFlights.add(this.getMockFlight(from, to, departureDate));
			}
		}
		return serviceFlights;
	}

	public static String formatedDate(DateTime dateTime, String pattern) {
		DateTimeFormatter df = DateTimeFormat.forPattern(pattern);
		return dateTime.toString(df);
	}

	private Flight getMockFlight(String from, String to, DateTime departureDate) {
		Integer index = RandomUtils.nextInt(3);
		Flight flight = this.mockFlightList.get(index);
		flight.setFrom(from);
		flight.setTo(to);
		flight.setSearchUrl(this.getSearchUrl(from, to, departureDate));

		return flight;
	}

	private String getSearchUrl(String from, String to, DateTime departureDate) {
		return FlightServiceConstants.SEARCH_URL_BASE
				+ "/"
				+ from
				+ "/"
				+ to
				+ "/"
				+ formatedDate(departureDate,
						FlightServiceConstants.SEARCH_DATE_PATTER) + "/1/0/0";
	}
}
