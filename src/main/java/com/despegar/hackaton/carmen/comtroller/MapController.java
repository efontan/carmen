
package com.despegar.hackaton.carmen.comtroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.despegar.hackaton.carmen.web.session.SessionService;
import com.despegar.library.rest.interceptors.HttpRequestContext;

@Controller
public class MapController{

    private static final String NAME_VIEW = "flights/index";

    @Autowired
    private SessionService sessionService;
    
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index(HttpRequestContext context, HttpServletRequest request)
          throws Exception {
        this.getSessionService().getSession(request);
        Map<String, Object> model = new HashMap<String, Object>();
        return new ModelAndView(NAME_VIEW, model);
    }

    public SessionService getSessionService() {
        return this.sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}

