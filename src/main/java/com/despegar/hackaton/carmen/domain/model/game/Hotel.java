package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Hotel {

	private Long id;
	private String name;
	private String description;
	private Coordinates position;
	private BigDecimal price;
	private int starsNumber;
	private String imageKey;
	private BigDecimal rating;

	public Hotel() {
	}

	public Hotel(Long id, String name, String description,
			Coordinates position, BigDecimal price, int starsNumber,
			String imageKey, BigDecimal rating) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.position = position;
		this.price = price;
		this.starsNumber = starsNumber;
		this.imageKey = imageKey;
		this.rating = rating;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Coordinates getPosition() {
		return this.position;
	}

	public void setPosition(Coordinates position) {
		this.position = position;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStarsNumber() {
		return this.starsNumber;
	}

	public void setStarsNumber(int starsNumber) {
		this.starsNumber = starsNumber;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageKey() {
		return this.imageKey;
	}

	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}

}
