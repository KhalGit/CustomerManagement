package com.example.customermanagement.exception;

public class NotFoundIdException extends RuntimeException   {

    public NotFoundIdException(Long id) {
        super(id + " is not found.");
    }
}
