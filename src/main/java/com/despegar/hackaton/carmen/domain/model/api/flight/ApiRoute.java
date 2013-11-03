package com.despegar.hackaton.carmen.domain.model.api.flight;

import java.util.List;

public class ApiRoute {

	private List<ApiFlightSegment> segments;

	public List<ApiFlightSegment> getSegments() {
		return this.segments;
	}

	public void setSegments(List<ApiFlightSegment> segments) {
		this.segments = segments;
	}

}
