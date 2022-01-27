package com.switchfully.codecoach.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected void exceptionHandling(Exception ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage() + " : " + response);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }
}
