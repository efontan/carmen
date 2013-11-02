package com.despegar.hackaton.carmen.domain.interceptor;

import java.io.IOException;

import com.despegar.hackaton.carmen.domain.constant.ApiConstant;
import com.despegar.library.rest.interceptors.HttpRequestContext;
import com.despegar.library.rest.interceptors.HttpResponseContext;
import com.despegar.library.rest.interceptors.Interceptor;

public class ApiHeadersInterceptor extends Interceptor {
	@Override
	protected HttpResponseContext preHandle(HttpRequestContext request)
			throws IOException {
		request.getHeaders().set(ApiConstant.API_HEADER_NAME, ApiConstant.API_KEY);
		return super.preHandle(request);
	}
}
