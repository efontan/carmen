package com.despegar.hackaton.carmen.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Player;
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
	private SessionService sessionService;

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpRequestContext context,
			HttpServletRequest request) throws Exception {
		this.getSessionService().getSession(request);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(NAME_VIEW, model);
	}

	@RequestMapping(value = "/initialize", method = RequestMethod.GET)
	public ResponseEntity<Object> initialize(HttpRequestContext context,
			HttpServletRequest request, HttpServletResponse response) {
		City city = this.getGameService().getCityData("BUE");
		return new ResponseEntity<Object>(new Response<City>(
				ResponseStatus.SUCCESS, city), HttpStatus.OK);
	}

	@RequestMapping(value = "/player/new", method = RequestMethod.POST)
	public ResponseEntity<Object> newPlayer(HttpRequestContext context,
			HttpServletRequest request, HttpServletResponse response,
			@RequestBody Player player) {
		GameSession gameSession = this.getGameService().createGameSession(
				player);
		this.getSessionService().createSession(request, response, gameSession);
		return new ResponseEntity<Object>(ResponseStatus.SUCCESS, HttpStatus.OK);
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

	@RequestMapping(value = "/flight/travel/{from}/{to}/{token}", method = RequestMethod.GET)
	public ResponseEntity<Object> flightTravel(HttpRequestContext context,
			HttpServletRequest request, @PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("token") String token) {
		// TODO

		TravelResponseDTO travelResponseDTO = new TravelResponseDTO();
		return new ResponseEntity<Object>(new Response<TravelResponseDTO>(
				ResponseStatus.SUCCESS, travelResponseDTO), HttpStatus.OK);
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

	public SessionService getSessionService() {
		return this.sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public GameService getGameService() {
		return this.gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
