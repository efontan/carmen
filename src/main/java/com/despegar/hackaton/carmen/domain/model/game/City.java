package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

	private static final long serialVersionUID = 221621173371639912L;

	private String code;
	private String name;
	private Coordinates position;
	private List<Hotel> hotels;
	private List<String> nextDestinations;

	public City() {
	}

	public City(String code, String name, Coordinates position,
			List<Hotel> hotels) {
		this.code = code;
		this.name = name;
		this.position = position;
		this.hotels = hotels;
	}
	
	public List<String> getNextDestinations() {
		return nextDestinations;
	}

	public void setNextDestinations(List<String> nextDestinations) {
		this.nextDestinations = nextDestinations;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public String getName() {
		return this.name;
	}

	public Coordinates getPosition() {
		return this.position;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
