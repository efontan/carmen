package com.despegar.hackaton.carmen.domain.dto;

public class PlayerDTO {

	private String name;
	private String email;
	private String genre;

	public PlayerDTO(String name, String email, String genre) {
		this.name = name;
		this.email = email;
		this.genre = genre;
	}

	public String getEmail() {
		return this.email;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getName() {
		return this.name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setName(String name) {
		this.name = name;
	}

}
