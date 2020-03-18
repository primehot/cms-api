package com.binance.cms.api.controller;

import com.binance.cms.api.exception.ImageNotFoundException;
import com.binance.cms.api.exception.InvalidImageException;
import com.binance.cms.api.exception.ItemNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity handleInvalidResourceException(RuntimeException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        HttpHeaders headers = getApplicationJsonHeaders();

        log.error(e.getMessage());
        return new ResponseEntity<>(ErrorDetails.of(e.getMessage()), headers, status);
    }

    @ExceptionHandler({MappingException.class})
    public ResponseEntity handleConvertException(MappingException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = getApplicationJsonHeaders();

        String errorMessages = e.getErrorMessages().stream().map(ErrorMessage::getMessage).collect(Collectors.joining(". "));
        log.error(errorMessages);
        return new ResponseEntity<>(ErrorDetails.of(errorMessages), headers, status);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseEntity handleDataBaseException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = getApplicationJsonHeaders();

        String errorMessages = "DataBase exception";
        log.error(e.getLocalizedMessage() + " " + e.getMessage());
        return new ResponseEntity<>(ErrorDetails.of(errorMessages), headers, status);
    }

    @ExceptionHandler({RollbackException.class})
    public ResponseEntity handleConstraintException(RollbackException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = getApplicationJsonHeaders();

        String errorMessages = "DataBase rollback exception";
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException casted = (ConstraintViolationException) e.getCause();
            String logMessage = casted.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            log.error(logMessage);
        } else {
            log.error(e.getMessage());
        }

        return new ResponseEntity<>(ErrorDetails.of(errorMessages), headers, status);
    }

    @ExceptionHandler({ImageNotFoundException.class})
    public ResponseEntity handleImageException(ImageNotFoundException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = getApplicationJsonHeaders();

        String errorMessages = e.getMessage();
        log.error(errorMessages);
        return new ResponseEntity<>(ErrorDetails.of(errorMessages), headers, status);
    }

    @ExceptionHandler({InvalidImageException.class})
    public ResponseEntity handleImageException(InvalidImageException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = getApplicationJsonHeaders();

        String errorMessages = e.getMessage();
        log.error(errorMessages);
        return new ResponseEntity<>(ErrorDetails.of(errorMessages), headers, status);
    }

    private HttpHeaders getApplicationJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Getter
    @Setter
    private static class ErrorDetails {
        private String description;

        ErrorDetails(String description) {
            this.description = description;
        }

        public static ErrorDetails of(String description) {
            return new ErrorDetails(description);
        }
    }
}
