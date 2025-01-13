package com.example.customermanagement.exception;

public class EmptyStringException extends RuntimeException  {

    public EmptyStringException(String stringColumn)    {
        super(stringColumn + " is null.");
    }
}
