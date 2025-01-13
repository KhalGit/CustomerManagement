package com.example.customermanagement.exception;

public class NegativeValueException extends RuntimeException    {

    public NegativeValueException(String priceOrQuantity) {
        super(priceOrQuantity + " is negative.");
    }

}
