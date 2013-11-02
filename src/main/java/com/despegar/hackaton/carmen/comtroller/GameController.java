package com.despegar.hackaton.carmen.comtroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.despegar.hackaton.carmen.domain.model.game.Status;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.web.controller.response.Response;
import com.despegar.hackaton.carmen.web.controller.response.ResponseStatus;
import com.despegar.hackaton.carmen.web.session.GameSession;
import com.despegar.hackaton.carmen.web.session.SessionService;
import com.despegar.hackaton.carmen.web.session.dto.HotelConfirmDTO;
import com.despegar.hackaton.carmen.web.session.dto.TravelResponseDTO;
import com.despegar.library.rest.interceptors.HttpRequestContext;

@Controller
public class GameController {

	private static final String NAME_VIEW = "game/index";

	@Autowired
	@Qualifier("sessionService")
	private SessionService sessionService;

	@Autowired
	@Qualifier("gameService")
	private GameService gameService;

	@RequestMapping(value = "/initialize", method = RequestMethod.GET)
	public ResponseEntity<Object> initialize(HttpRequestContext context,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO

		return new ResponseEntity<Object>(new Response<Object>(
				ResponseStatus.SUCCESS, null), HttpStatus.OK);
	}

	@RequestMapping(value = "/flight/travel/{from}/{to}/{token}", method = RequestMethod.GET)
	public ResponseEntity<Object> flightTravel(HttpRequestContext context,
			HttpServletRequest request, @PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("token") String token) {
		// TODO

		TravelResponseDTO travelResponseDTO = new TravelResponseDTO();
		return new ResponseEntity<Object>(new Response<TravelResponseDTO>(
				ResponseStatus.SUCCESS, travelResponseDTO), HttpStatus.OK);
	}

	public SessionService getSessionService() {
		return this.sessionService;
	}

	@RequestMapping(value = "/status/{token}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStatus(HttpRequestContext context,
			HttpServletRequest request, @PathVariable("token") String token) {
		// TODO
		GameSession gameSession = this.getSessionService().getSession(request)
				.getGameSessionByToken(token);
		Status status = gameSession.getStatus();
		return new ResponseEntity<Object>(new Response<Status>(
				ResponseStatus.SUCCESS, status), HttpStatus.OK);
	}

	@RequestMapping(value = "/hotel/confirm/{hotelId}/{token}", method = RequestMethod.POST)
	public ResponseEntity<Object> hotelConfirm(HttpRequestContext context,
			HttpServletRequest request, @PathVariable("hotelId") Long hotelId,
			@PathVariable("token") String token) {
		// TODO

		new HotelConfirmDTO();
		return new ResponseEntity<Object>(new Response<Object>(
				ResponseStatus.SUCCESS, null), HttpStatus.OK);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpRequestContext context,
			HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(NAME_VIEW, model);
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
