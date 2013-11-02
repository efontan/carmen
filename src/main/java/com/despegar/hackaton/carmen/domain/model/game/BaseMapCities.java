package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

public class BaseMapCities implements Serializable {

	private static final long serialVersionUID = 5780166496506205957L;

	private List<City> cities;

	public BaseMapCities() {
	}

	public BaseMapCities(List<City> cities) {
		this.cities = cities;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
