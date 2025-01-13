package com.example.customermanagement.exception;

import com.example.customermanagement.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomerManagementExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NegativeValueException exc){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
