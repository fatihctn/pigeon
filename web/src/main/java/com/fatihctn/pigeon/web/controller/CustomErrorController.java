package com.fatihctn.pigeon.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);


        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                writeLog(status.toString(), request);
                return "error/error_404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                writeLog(status.toString(), request);
                return "error/error_500";
            }
        }
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private void writeLog(String status, HttpServletRequest request) {
        logger.info("[Access-Error] error-type: {}, request-path: {}, user-ip: {}",
                status, request.getRequestURI(), request.getRemoteAddr());
    }
}
