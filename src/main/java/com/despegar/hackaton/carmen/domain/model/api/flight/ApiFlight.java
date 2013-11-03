package com.despegar.hackaton.carmen.domain.model.api.flight;

public class ApiFlight {

	private ApiOutboundRoutes outboundRoutes;
	private ApiPriceInfo priceInfo;

	public ApiOutboundRoutes getOutboundRoutes() {
		return this.outboundRoutes;
	}

	public void setOutboundRoutes(ApiOutboundRoutes outboundRoutes) {
		this.outboundRoutes = outboundRoutes;
	}

	public ApiPriceInfo getPriceInfo() {
		return this.priceInfo;
	}

	public void setPriceInfo(ApiPriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}
}
