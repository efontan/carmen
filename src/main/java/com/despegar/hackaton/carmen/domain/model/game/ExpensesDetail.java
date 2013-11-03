package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class ExpensesDetail implements Serializable {

	private static final long serialVersionUID = -8452514669632201965L;
	private List<FlightExpense> flightExpenses;
	private List<HotelExpense> hotelExpenses;

	public ExpensesDetail() {
		this.flightExpenses = Lists.newArrayList();
		this.hotelExpenses = Lists.newArrayList();
	}

	public List<FlightExpense> getFlightExpenses() {
		return this.flightExpenses;
	}

	public void setFlightExpenses(List<FlightExpense> flightExpenses) {
		this.flightExpenses = flightExpenses;
	}

	public List<HotelExpense> getHotelExpenses() {
		return this.hotelExpenses;
	}

	public void setHotelExpenses(List<HotelExpense> hotelExpenses) {
		this.hotelExpenses = hotelExpenses;
	}

}
