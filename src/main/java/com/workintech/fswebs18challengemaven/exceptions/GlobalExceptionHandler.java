package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<ApiError> handleCardException(CardException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                ex.getStatus().value()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                500
        );
        return ResponseEntity.status(500).body(error);
    }
}
