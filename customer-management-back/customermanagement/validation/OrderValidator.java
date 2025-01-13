package com.example.customermanagement.validation;

import com.example.customermanagement.entity.OrderStatus;
import com.example.customermanagement.exception.NegativeValueException;
import com.example.customermanagement.exception.NotFoundIdException;
import com.example.customermanagement.exception.NullEntityException;
import com.example.customermanagement.exception.NullEnumException;
import com.example.customermanagement.repository.ContactRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ContactRepository contactRepository;

    public void idNotFound(Long id) {
        if (orderRepository.findById(id).isEmpty())   {
            throw new NotFoundIdException(id);
        }
    }

    public void amountEmpty(Long amount)    {
        if (amount == null || amount < 0)   {
            throw new NegativeValueException(amount.toString());
        }
    }

    public void customerNull(Long id)  {
        if (!customerRepository.existsById(id)) {
            throw new NullEntityException(id);
        }
    }

    public void contactNull(Long id)  {
        if (!contactRepository.existsById(id)) {
            throw new NullEntityException(id);
        }
    }

    public void orderStatusNull(OrderStatus orderStatus)  {
        if (orderStatus == null || (orderStatus != OrderStatus.PENDING && orderStatus != OrderStatus.COMPLETED)) {
            throw new NullEnumException(orderStatus);
        }
    }
}
