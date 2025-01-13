package com.example.customermanagement.service;


import com.example.customermanagement.dto.OrderDTO;
import com.example.customermanagement.entity.Contact;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.entity.Order;
import com.example.customermanagement.exception.DataNotFound;
import com.example.customermanagement.repository.ContactRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.repository.OrderRepository;
import com.example.customermanagement.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OrderValidator orderValidator;

    @Override
    public List<OrderDTO> findAllOrders()   {
        List<Order> theOrders = orderRepository.findAll();
        List<OrderDTO> theOrdersDTO = new ArrayList<>();

        for (Order o: theOrders)    {
            theOrdersDTO.add(new OrderDTO(o));
        }

        if (theOrdersDTO.isEmpty()) {
            DataNotFound dataNotFound;
        }
        return theOrdersDTO;
    }

    @Override
    public OrderDTO findByOrderId(Long orderId) {
        orderValidator.idNotFound(orderId);
        Order theOrder = orderRepository.findById(orderId).orElseThrow(ArithmeticException::new);

        return new OrderDTO(theOrder);
    }
    @Override
    public OrderDTO addOrder(OrderDTO theOrderDTO)  {
        orderValidator.amountEmpty(theOrderDTO.getAmount());
        orderValidator.customerNull(theOrderDTO.getCustomerId());
        orderValidator.contactNull(theOrderDTO.getContactId());

        Order theOrder = new Order(theOrderDTO);
        Customer customer = customerRepository.getOne(theOrderDTO.getCustomerId());
        Contact contact = contactRepository.getOne(theOrderDTO.getContactId());

        theOrder.setCustomer(customer);
        theOrder.setContact(contact);
        return toDTO(theOrder);

    }
    @Override
    public void deleteByOrderId(Long orderId)   {
        orderValidator.idNotFound(orderId);
        orderRepository.deleteById(orderId);
    }
    @Override
    public OrderDTO updateOrder(OrderDTO theOrderDTO, Long orderId) {
        orderValidator.idNotFound(orderId);
        orderValidator.amountEmpty(theOrderDTO.getAmount());
        orderValidator.customerNull(theOrderDTO.getCustomerId());
        orderValidator.contactNull(theOrderDTO.getContactId());
        orderValidator.orderStatusNull(theOrderDTO.getOrderStatus());

        Order theOrder = orderRepository.findById(orderId).get();
        Customer customer = customerRepository.getOne(theOrderDTO.getCustomerId());
        Contact contact = contactRepository.getOne(theOrderDTO.getContactId());

        if (Objects.nonNull(theOrderDTO.getAmount()) && !"".equals(theOrderDTO.getAmount()))    {
            theOrder.setAmount(theOrderDTO.getAmount());
        }

        theOrder.setCustomer(customer);
        theOrder.setContact(contact);

        return toDTO(theOrder);
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO(order);
        return dto;
    }

}
