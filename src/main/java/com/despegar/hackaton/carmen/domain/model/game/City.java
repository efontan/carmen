package com.despegar.hackaton.carmen.domain.model.game;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

	private static final long serialVersionUID = 221621173371639912L;

	private Long id;
	private String name;
	private Coordinates position;
	private List<Hotel> hotels;

	public City() {
	}

	public City(Long id, String name, Coordinates position, List<Hotel> hotels) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.hotels = hotels;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public Long getId() {
		return this.id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}

}
