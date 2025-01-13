package com.example.customermanagement.validation;

import com.example.customermanagement.exception.NotFoundIdException;
import com.example.customermanagement.exception.NullEntityException;
import com.example.customermanagement.repository.ContactRepository;
import com.example.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CustomerRepository customerRepository;
    public void idNotFound(Long id) {
        if (contactRepository.findById(id).isEmpty())   {
            throw new NotFoundIdException(id);
        }
    }

    public void customerNull(Long id)  {
        if (!customerRepository.existsById(id)) {
            throw new NullEntityException(id);
        }
    }
}
