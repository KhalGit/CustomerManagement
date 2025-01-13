package com.example.customermanagement.service;


import com.example.customermanagement.dto.ContactDTO;
import com.example.customermanagement.dto.CustomerDTO;
import com.example.customermanagement.dto.OrderDTO;
import com.example.customermanagement.entity.Contact;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.entity.Order;
import com.example.customermanagement.exception.DataNotFound;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.validation.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerValidator customerValidator;


    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> theCustomers = customerRepository.findAll();
        List<CustomerDTO> theCustomersDTO = new ArrayList<>();

        for (Customer c: theCustomers)  {                           //for each Customer object create a new DTO
            theCustomersDTO.add(new CustomerDTO(c));
        }
        if (theCustomersDTO.isEmpty())  {                           //if there is no data call DataNotFound exception
            DataNotFound dataNotFound;
        }
        return theCustomersDTO;
    }

    @Override
    public CustomerDTO findByCustomerAFM(Long customerAFM)  {
        customerValidator.AFMNotFound(customerAFM);                         //if AFM is not found throw exception
        Customer theCustomer = customerRepository.findById(customerAFM).orElseThrow(ArithmeticException::new);      //if  ArithmeticException is thrown,
        return new CustomerDTO(theCustomer);                                                                        //then customerValidator doesnt work properly
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO theCustomerDTO)  {
        customerValidator.AFMEmpty(theCustomerDTO.getAFM());                                            //exception for wrong info added by user
        customerValidator.lastNameEmpty(theCustomerDTO.getLastName());                                  //exception for wrong info added by user

        Customer theCustomer = new Customer(theCustomerDTO);

        for (ContactDTO c: theCustomerDTO.getCustomersContactList())    {                       //creates a new contactList for this Customer
            c.setId(theCustomerDTO.getId());
            Contact contact = new Contact(c);
            theCustomer.getCustomersContactList().add(contact);
        }

        for (OrderDTO o: theCustomerDTO.getCustomersOrderList())    {                           //creates a new orderList for this Customer
            o.setId(theCustomerDTO.getId());
            Order order = new Order(o);
            theCustomer.getCustomersOrderList().add(order);
        }

        theCustomer = customerRepository.save(theCustomer);
        return toDTO(theCustomer);
    }

    @Override
    public void deleteByCustomerAFM(Long customerAFM)   {
        customerValidator.AFMNotFound(customerAFM);                                 //checks customerAFM validity
        customerRepository.deleteById(customerAFM);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO theCustomerDTO, Long customerAFM) {
        customerValidator.AFMNotFound(customerAFM);                                         //checks customerAFM validity
        customerValidator.lastNameEmpty(theCustomerDTO.getLastName());                      //checks if customerLastName is null
        Customer theCustomer = customerRepository.findById(customerAFM).get();

        if (Objects.nonNull(theCustomerDTO.getLastName()) && !"".equalsIgnoreCase(theCustomerDTO.getLastName()))    {
            theCustomer.setLastName(theCustomerDTO.getLastName());
        }
        theCustomer = customerRepository.save(theCustomer);
        return toDTO(theCustomer);
    }

    private CustomerDTO toDTO(Customer customer)    {                       //copies customersOrderList, customersContactList from the entity to dto
        CustomerDTO dto = new CustomerDTO(customer);
        for (Order o : customer.getCustomersOrderList())    {               //for-loop to add items in list of orderDTO
            dto.getCustomersOrderList().add(new OrderDTO(o));
        }

        for (Contact c : customer.getCustomersContactList())    {           //for-loop to add items in list of contactDTO
            dto.getCustomersContactList().add(new ContactDTO(c));
        }

        return dto;
    }
}