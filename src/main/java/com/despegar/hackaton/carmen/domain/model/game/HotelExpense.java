package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.math.BigDecimal;

public class HotelExpense implements Serializable {

	private static final long serialVersionUID = 6170978559009796347L;
	private String name;
	private BigDecimal price;

	public HotelExpense() {
	}

	public HotelExpense(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
