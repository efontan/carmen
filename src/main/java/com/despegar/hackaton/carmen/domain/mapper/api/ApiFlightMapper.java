package com.despegar.hackaton.carmen.domain.mapper.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.despegar.hackaton.carmen.domain.client.geo.CitiesRestClient;
import com.despegar.hackaton.carmen.domain.mapper.Mapper;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlight;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlightSegment;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlightSegmentDetails;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiPriceInfo;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiRoute;
import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.despegar.hackaton.carmen.domain.service.impl.FlightServiceConstants;

@Component("apiFlightMapper")
public class ApiFlightMapper implements Mapper<ApiFlight, Flight> {

	private static final int MILLIS_PER_HOUR = 60 * 60 * 1000;

	@Autowired
	@Qualifier("cities.rest.client")
	private CitiesRestClient citiesRestClient;

	@Resource
	@Qualifier("citiesMap")
	private Map<String, String> citiesMap;

	@Override
	public Flight map(ApiFlight apiFlight) {
		Flight flight = new Flight();

		ApiRoute firstRoute = apiFlight.getRoutes().get(0);
		List<ApiFlightSegment> segments = firstRoute.getSegments();
		ApiFlightSegment firstApiFlightSegment = segments.get(0);
		ApiFlightSegmentDetails departure = firstApiFlightSegment
				.getDeparture();

		int totalSegments = segments.size();
		ApiFlightSegment lastApiFlightSegment = segments.get(totalSegments - 1);
		ApiFlightSegmentDetails arrival = lastApiFlightSegment.getArrival();

		String from = this.citiesRestClient.getCityCodeByAirportId(departure
				.getLocation());
		String to = this.citiesRestClient.getCityCodeByAirportId(arrival
				.getLocation());

		String duration = firstApiFlightSegment.getDuration();

		ApiPriceInfo priceInfo = apiFlight.getPriceInfo();

		flight.setDepartureDate(this.getDateFromFlight(departure.getTimezone()));
		flight.setDurationHours(this.getDurationHours(duration));
		flight.setFrom(from);
		flight.setTo(to);
		flight.setFromCityName(this.citiesMap.get(from));
		flight.setToCityName(this.citiesMap.get(to));
		flight.setStops(segments.size());
		flight.setPrice(priceInfo.getAdults().getBaseFare());
		flight.setSearchUrl(this.getSearchUrl(from, to));

		return flight;
	}

	private String getSearchUrl(String from, String to) {
		DateTime departureDate = DateTime.now().plusMonths(
				FlightServiceConstants.PLUS_MONTHS);
		return FlightServiceConstants.SEARCH_URL_BASE
				+ "/"
				+ from
				+ "/"
				+ to
				+ "/"
				+ formatedDate(departureDate,
						FlightServiceConstants.SEARCH_DATE_PATTER) + "/1/0/0";
	}

	private String getDateFromFlight(Double offset) {
		Date date = new Date();
		DateTime dateTimeForOffset = null;
		dateTimeForOffset = this.getDateTimeForOffset(date, offset);
		return dateTimeForOffset.toString("dd-MM-yyyy HH:mm");
	}

	private DateTime getDateTimeForOffset(Date fromDate, Double offsetInHours) {
		Integer offsetInMillis = (int) (offsetInHours * MILLIS_PER_HOUR);
		DateTimeZone dtz = DateTimeZone.forOffsetMillis(offsetInMillis);
		return new DateTime(fromDate, dtz);
	}

	private Integer getDurationHours(String duration) {
		String[] split = duration.split(":");
		Integer hours = Integer.parseInt(split[0]);
		Integer minutes = Integer.parseInt(split[1]);
		if (minutes > 30) {
			hours++;
		}
		return hours;
	}

	public static String formatedDate(DateTime dateTime, String pattern) {
		DateTimeFormatter df = DateTimeFormat.forPattern(pattern);
		return dateTime.toString(df);
	}

}
