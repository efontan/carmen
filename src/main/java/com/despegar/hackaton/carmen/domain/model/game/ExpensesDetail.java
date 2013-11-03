package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;
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

	public BigDecimal getFlightSummary(){
		BigDecimal flightSummary = BigDecimal.ZERO;
		for(FlightExpense flight : this.flightExpenses){
			flightSummary.add(flight.getPrice());
		}
		return flightSummary;
	}
	
	public BigDecimal getHotelSummary(){
		BigDecimal hotelSummary = BigDecimal.ZERO;
		for(HotelExpense hotel : this.hotelExpenses){
			hotelSummary.add(hotel.getPrice());
		}
		return hotelSummary;
	}
	
	public BigDecimal getSummary(){
		return this.getFlightSummary().add(this.getHotelSummary());
	}
}
