package com.bank.loan.customer_service.exception;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest req) {
        return new ResponseEntity<>(new ApiError(LocalDateTime.now(), ex.getMessage(), req.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex, WebRequest req) {
        return new ResponseEntity<>(new ApiError(LocalDateTime.now(), ex.getMessage(), req.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


