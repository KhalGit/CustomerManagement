package com.example.customermanagement.exception;

public class NotFoundAFMException extends RuntimeException   {

    public NotFoundAFMException(Long id) {
        super(id + " is not found.");
    }
}
