package com.example.controllerdemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class ProcessingTimeLogInterceptor {
    // interceptor will work for any controllers i execute and create on this workspace.
    private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletRequest response, Object handler) {
        long startTime = System.currentTimeMillis();
        // donnid to remember for very long time so put in request
        request.setAttribute("startTime", startTime);
        // should see in console
        LOGGER.warning("STARTS HERE");

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        String path = request.getRequestURL() + queryString;

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        LOGGER.info(String.format("%s millisecond taken to process the request %s.", (endTime - startTime), path));
    }


}
