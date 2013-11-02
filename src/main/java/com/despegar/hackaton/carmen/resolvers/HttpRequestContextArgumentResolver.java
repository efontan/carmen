package com.despegar.hackaton.carmen.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.despegar.library.rest.interceptors.HttpRequestContext;

public class HttpRequestContextArgumentResolver implements
		HandlerMethodArgumentResolver {
	public static final String HTTP_REQUEST_CONTEXT_ATTRIBUTE_NAME = "HTTP_REQUEST_CONTEXT";
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return HttpRequestContext.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if (parameter.getParameterType().equals(HttpRequestContext.class)) {
            return webRequest.getAttribute(HTTP_REQUEST_CONTEXT_ATTRIBUTE_NAME, NativeWebRequest.SCOPE_REQUEST);
        }
        return null;
	}

}
