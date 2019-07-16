package com.bht.controllers;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

// @ControllerAdvice annotation use for
// Handling exceptions across the whole application
// Not just an individual controller
// Other things to remind
// Work going well until dev noticed
// application crashed in some cases
// approach is to use @ExceptionHandler
// in each Controller as below eg.
// @ExceptionHandler(UserNotFoundException.class)
// There is an exception class we created for handling
// Eg. class UserNotFoundException {} ...
@ControllerAdvice
public class ExceptionController {

    private Logger logger = Logger.getRootLogger();

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandler(Exception exception) {

        logger.warn(exception);
        return "error/handler";
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public String noConnection(Exception exception) {

        logger.warn(exception);
        return "error/connection";
    }
}
