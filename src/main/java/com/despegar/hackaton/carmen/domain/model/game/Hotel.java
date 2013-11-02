package com.despegar.hackaton.carmen.domain.model.game;

import java.math.BigDecimal;

/**
 * @author emiliano.lourbet - taitooz - elourbet[at]despegar[dot]com.
 */
public class Hotel {

	private String name;
	private String description;
	private Coordinates position;
	private BigDecimal price;
	private int starsNumber;
	private String imageUri;
	private BigDecimal rating;

	public Hotel() {
	}

	public Hotel(String name, String description, Coordinates position,
			BigDecimal price, int starsNumber, String imageUri,
			BigDecimal rating) {
		this.name = name;
		this.description = description;
		this.position = position;
		this.price = price;
		this.starsNumber = starsNumber;
		this.imageUri = imageUri;
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

	public String getImageUri() {
		return this.imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

}
