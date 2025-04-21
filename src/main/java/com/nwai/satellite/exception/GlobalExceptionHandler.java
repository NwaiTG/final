package com.nwai.satellite.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        ApiError errorMsg = new ApiError(
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMsg);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        ApiError errorMsg = new ApiError(
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }
}
