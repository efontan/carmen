package com.despegar.hackaton.carmen.comtroller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.despegar.library.rest.interceptors.HttpRequestContext;
import com.google.common.collect.Maps;

public class BaseController {
	protected ModelAndView createModelAndView(HttpRequestContext context,
			String viewName) {
		Map<String, Object> model = Maps.newHashMap();
		return this.createModelAndView(context, viewName, model);
	}

	protected ModelAndView createModelAndView(HttpRequestContext context,
			String viewName, Map<String, Object> model) {
		ModelAndView modelAndView = new ModelAndView(viewName, model);
		return modelAndView;
	}

}
