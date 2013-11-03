package com.despegar.hackaton.carmen.domain.model.api.geo;

public class ApiCity {
	private String id;
	private long internalId;
	private String name;
	private ApiGeoLocation geoLocation;

	public ApiGeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(ApiGeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getInternalId() {
		return internalId;
	}

	public void setInternalId(long internalId) {
		this.internalId = internalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
