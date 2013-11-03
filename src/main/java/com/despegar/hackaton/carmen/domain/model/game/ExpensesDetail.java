package com.despegar.hackaton.carmen.domain.model.game;


import java.math.BigDecimal;
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

    public void addHotelExpense(HotelExpense expense) {
        hotelExpenses.add(expense);
    }

	public void setHotelExpenses(List<HotelExpense> hotelExpenses) {
		this.hotelExpenses = hotelExpenses;
	}

	public BigDecimal getFlightSummary(){
		BigDecimal flightSummary = BigDecimal.ZERO;
		for(FlightExpense flight : this.flightExpenses){
			flightSummary = flightSummary.add(flight.getPrice());
		}
		return flightSummary;
	}
	
	public BigDecimal getHotelSummary(){
		BigDecimal hotelSummary = BigDecimal.ZERO;
		for(HotelExpense hotel : this.hotelExpenses){
			hotelSummary = hotelSummary.add(hotel.getPrice());
		}
		return hotelSummary;
	}
	
	public BigDecimal getSummary(){
		return this.getFlightSummary().add(this.getHotelSummary());
	}
}
