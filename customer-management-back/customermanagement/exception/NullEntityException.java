package com.example.customermanagement.exception;

public class NullEntityException extends RuntimeException   {
    public NullEntityException(Object object)   {
        super(object.toString() + " is empty.");
    }
}
