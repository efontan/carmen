package com.despegar.hackaton.carmen.domain.model.game;

import java.util.List;

public class ExpensesDetail {
	private List<FlightExpense> flightExpenses;
	private List<HotelExpense> hotelExpenses;

	public List<FlightExpense> getFlightExpenses() {
		return flightExpenses;
	}

	public void setFlightExpenses(List<FlightExpense> flightExpenses) {
		this.flightExpenses = flightExpenses;
	}

	public List<HotelExpense> getHotelExpenses() {
		return hotelExpenses;
	}

	public void setHotelExpenses(List<HotelExpense> hotelExpenses) {
		this.hotelExpenses = hotelExpenses;
	}

}
