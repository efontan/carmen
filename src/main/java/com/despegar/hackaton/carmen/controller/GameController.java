package com.despegar.hackaton.carmen.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.despegar.library.rest.interceptors.HttpRequestContext;

public class GameController extends BaseController {
	private static final String INDEX_VIEW = "game/index";

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpRequestContext context,
			HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return this.createModelAndView(context, INDEX_VIEW, model);
	}

}
