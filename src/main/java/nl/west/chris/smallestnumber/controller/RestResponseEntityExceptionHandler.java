package nl.west.chris.smallestnumber.controller;

import nl.west.chris.smallestnumber.exception.InvalidNumberOrderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { InvalidNumberOrderException.class })
    protected ResponseEntity<Object> handleInvalidNumberOrder(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, new ExceptionResponse(
                "Invalid request, startNumber is not smaller than endNumber", HttpStatus.BAD_REQUEST.value()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
