package com.despegar.hackaton.carmen.domain.mapper.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.despegar.hackaton.carmen.domain.mapper.Mapper;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlight;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlightSegment;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiFlightSegmentDetails;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiPriceInfo;
import com.despegar.hackaton.carmen.domain.model.api.flight.ApiRoute;
import com.despegar.hackaton.carmen.domain.model.game.Flight;

public class ApiFlightMapper implements Mapper<ApiFlight, Flight> {

	private static final int MILLIS_PER_HOUR = 60 * 60 * 1000;

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

		String from = departure.getLocation();
		String to = arrival.getLocation();

		String duration = firstApiFlightSegment.getDuration();

		ApiPriceInfo priceInfo = apiFlight.getPriceInfo();

		flight.setDepartureDate(this.getDateFromFlight(departure.getDate(),
				departure.getTimezone()));
		flight.setDurationHours(this.getDurationHours(duration));
		flight.setFrom(from);
		flight.setTo(to);
		flight.setStops(segments.size());
		flight.setPrice(priceInfo.getAdults().getBaseFare());
		flight.setSearchUrl("www.despegar.com");

		return flight;
	}

	private String getDateFromFlight(String date, Double offset) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateTime dateTimeForOffset = null;
		try {
			dateTimeForOffset = this.getDateTimeForOffset(sdf.parse(date),
					offset);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

}
