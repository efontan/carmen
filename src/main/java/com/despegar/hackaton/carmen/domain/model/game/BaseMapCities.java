package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

public class BaseMapCities implements Serializable {

	private static final long serialVersionUID = 5780166496506205957L;

	private List<Province> provinces;

	public BaseMapCities(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<Province> getProvinces() {
		return this.provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

}
