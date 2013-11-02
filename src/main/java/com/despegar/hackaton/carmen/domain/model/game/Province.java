package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

public class Province implements Serializable {

	private static final long serialVersionUID = 6678607783558952829L;

	private Long code;
	private String name;
	private List<City> cities;

	public Province() {
	}

	public Province(Long code, String name, List<City> cities) {
		this.code = code;
		this.name = name;
		this.cities = cities;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public Long getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
