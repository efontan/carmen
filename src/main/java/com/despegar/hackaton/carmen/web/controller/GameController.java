package com.despegar.hackaton.carmen.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.despegar.hackaton.carmen.domain.model.game.City;
import com.despegar.hackaton.carmen.domain.model.game.Clue;
import com.despegar.hackaton.carmen.domain.model.game.Flight;
import com.despegar.hackaton.carmen.domain.model.game.GraphNode;
import com.despegar.hackaton.carmen.domain.model.game.Player;
import com.despegar.hackaton.carmen.domain.model.game.Status;
import com.despegar.hackaton.carmen.domain.model.game.response.ClueResponse;
import com.despegar.hackaton.carmen.domain.model.game.response.TravelResponse;
import com.despegar.hackaton.carmen.domain.service.FlightService;
import com.despegar.hackaton.carmen.domain.service.GameService;
import com.despegar.hackaton.carmen.web.controller.response.Response;
import com.despegar.hackaton.carmen.web.controller.response.ResponseStatus;
import com.despegar.hackaton.carmen.web.session.GameSession;
import com.despegar.hackaton.carmen.web.session.SessionService;
import com.despegar.library.rest.interceptors.HttpRequestContext;

@Controller
public class GameController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private static final int TOTAL_CLUES = 0;

	private static final String NAME_VIEW = "game/index";
	private static final String UNDER = "_";

	@Autowired
	private SessionService sessionService;

	@Autowired
	private GameService gameService;

	@Autowired
	private FlightService flightService;

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

	@RequestMapping(value = "/clue/{token}/{cityCode}/{hotelId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getClue(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("token") String token,
			@PathVariable("cityCode") String cityCode,
			@PathVariable("hotelId") String hotelId) {
		GameSession gameSession = this.sessionService.getGameSessionByToken(
				request, token);
		GraphNode node = (GraphNode) this.applicationContext
				.getBean(gameSession.getGameWalkthrough() + UNDER + cityCode);
		int actualClue = gameSession.getActualClue();
		gameSession.setActualClue(actualClue == TOTAL_CLUES ? 0 : actualClue++); // increment
																					// to
																					// the
																					// nextClue
																					// or
																					// init
																					// 0.
		Clue clue = node.getClues().getClueByIndex(actualClue);
		Status newStatus = gameSession.getStatus(); // Update the game status.
		newStatus.setActualDate(newStatus.getActualDate().plusHours(8));
		// TODO:taitooz -> retrieve hotel price with hotelId.
		BigDecimal hotelPrice = new BigDecimal(0);
		newStatus.setRemainingMoney(newStatus.getRemainingMoney().subtract(
				hotelPrice));
		gameSession.setStatus(newStatus);
		this.sessionService.addGameSessionToSessions(request, response, token,
				gameSession);
		ClueResponse clueResponse = new ClueResponse(clue,
				gameSession.getStatus());
		return new ResponseEntity<Object>(new Response<Object>(
				ResponseStatus.SUCCESS, clueResponse), HttpStatus.OK);
	}

    @RequestMapping(value = "/travel/{token}/{cityCode}/{price}/{hours}", method = RequestMethod.GET)
    public ResponseEntity<Object> doTravel(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("token") String token,
                                          @PathVariable("cityCode") String cityCode,
                                          @PathVariable("price") BigDecimal price,
                                          @PathVariable("hours") Integer hours) {
        GameSession gameSession = sessionService.getGameSessionByToken(request, token);
        GraphNode node = (GraphNode) applicationContext.getBean(gameSession.getGameWalkthrough() + UNDER + cityCode);
        Status newStatus = gameSession.getStatus();
        //Flight flight = flightService.getFlight(searchHash, itineraryId); //This service is deprecated :P
        BigDecimal remainingMoney = newStatus.getRemainingMoney().subtract(price);
        newStatus.setRemainingMoney(remainingMoney);
        newStatus.setActualDate(newStatus.getActualDate().plus(hours));
        gameSession.setStatus(newStatus);
        sessionService.addGameSessionToSessions(request, response, token, gameSession);
        TravelResponse travelResponse = new TravelResponse(node, gameSession.getStatus());
        return new ResponseEntity<Object>(new Response<Object>(ResponseStatus.SUCCESS, travelResponse), HttpStatus.OK);
    }

	@RequestMapping(value = "/flights/{from}/{to}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFlights(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("from") String from,
			@PathVariable("to") String to) {
		List<Flight> flights = this.flightService.getFlights(from, to);
		return new ResponseEntity<Object>(new Response<Object>(
				ResponseStatus.SUCCESS, flights), HttpStatus.OK);
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
