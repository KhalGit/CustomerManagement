package com.example.customermanagement.validation;

import com.example.customermanagement.exception.EmptyStringException;
import com.example.customermanagement.exception.NegativeValueException;
import com.example.customermanagement.exception.NotFoundAFMException;
import com.example.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    @Autowired
    CustomerRepository customerRepository;

    public void AFMEmpty(Long AFM)    {
        if (AFM == null || AFM < 0) {
            throw new NegativeValueException(AFM.toString());
        }
    }

    public void lastNameEmpty(String lastName)    {
        if (lastName == null || lastName.isEmpty()) {
            throw new EmptyStringException(lastName);
        }
    }

    public void AFMNotFound(Long AFM) {
        if (customerRepository.findById(AFM).isEmpty())   {
            throw new NotFoundAFMException(AFM);
        }
    }

}
