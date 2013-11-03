package com.despegar.hackaton.carmen.config;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.google.common.collect.Lists;

@Configuration
public class FlightsMockMapConfig {

	private static final int MILLIS_PER_HOUR = 60 * 60 * 1000;

	@Bean(name = "mockFlightList")
	public List<Flight> getBrandMap() {
		List<Flight> mockFlights = Lists.newArrayList();
		String departureDate = this.getDateFromFlight(-3.0);

		Flight flight1 = new Flight();
		flight1.setDepartureDate(departureDate);
		flight1.setDurationHours(3);
		flight1.setFrom("");
		flight1.setTo("");
		flight1.setStops(1);
		flight1.setPrice(new BigDecimal(758));
		flight1.setSearchUrl("");
		mockFlights.add(flight1);

		Flight flight2 = new Flight();
		flight2.setDepartureDate(departureDate);
		flight2.setDurationHours(2);
		flight2.setFrom("");
		flight2.setTo("");
		flight2.setStops(1);
		flight2.setPrice(new BigDecimal(562));
		flight2.setSearchUrl("");
		mockFlights.add(flight2);

		Flight flight3 = new Flight();
		flight3.setDepartureDate(departureDate);
		flight3.setDurationHours(4);
		flight3.setFrom("");
		flight3.setTo("");
		flight3.setStops(2);
		flight3.setPrice(new BigDecimal(499));
		flight3.setSearchUrl("");
		mockFlights.add(flight3);

		return mockFlights;
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
}
