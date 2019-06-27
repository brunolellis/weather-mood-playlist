package io.github.brunolellis.playlist.adapter.web;

import io.github.brunolellis.playlist.port.out.CityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { CityNotFoundException.class })
    protected ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(List.of(new ErrorMessage(ex.getMessage())));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiErrorResponse response = new ApiErrorResponse(List.of(new ErrorMessage(ex.getMessage())));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleInternalError(Exception ex) {
        LOG.error("unexpected exception occurred", ex);

        ApiErrorResponse response = new ApiErrorResponse(List.of(new ErrorMessage("internal error")));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
