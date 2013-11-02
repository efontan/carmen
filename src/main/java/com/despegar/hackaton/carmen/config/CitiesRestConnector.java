package com.despegar.hackaton.carmen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.despegar.hackaton.carmen.domain.constant.ApiConstant;
import com.despegar.hackaton.carmen.domain.interceptor.ApiHeadersInterceptor;
import com.despegar.library.rest.RestConnector;
import com.despegar.library.rest.RestConnectorFactory;
import com.despegar.library.rest.config.RestConnectorConfig;
import com.despegar.library.rest.serializers.json.ObjectMapperFactory.JsonPropertiesFormat;

@Configuration
public class CitiesRestConnector {
	private String host = "api.despegar.com";
	private String baseUrl = "cities";

	@Bean(name = "cities.rest.connector")
	public RestConnector getHotelRestConnector() {
		RestConnector restConnector = RestConnectorFactory.createRestConnector(
				"http", this.host, this.baseUrl, true, ApiConstant.CLIENT_ID,
				ApiConstant.API_VERSION,
				RestConnectorConfig.createBuilder().readTimeout(3000)
						.jsonPropertiesFormat(JsonPropertiesFormat.CAMEL_CASE)
						.build());
		restConnector.getInterceptors().add(new ApiHeadersInterceptor());
		return restConnector;
	}

}
