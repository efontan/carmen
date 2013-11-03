package com.despegar.hackaton.carmen.domain.model.api.flight;

import java.util.List;

public class ApiFlight {

	private List<ApiRoute> routes;
	private ApiPriceInfo priceInfo;

	public List<ApiRoute> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<ApiRoute> routes) {
		this.routes = routes;
	}

	public ApiPriceInfo getPriceInfo() {
		return this.priceInfo;
	}

	public void setPriceInfo(ApiPriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}
}
