package com.example.customermanagement.service;

import com.example.customermanagement.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public List<CustomerDTO> findAllCustomers();

    public CustomerDTO findByCustomerAFM(Long customersAFM);

    public CustomerDTO addCustomer(CustomerDTO theCustomerDTO);

    public void deleteByCustomerAFM(Long customersAFM);

    public CustomerDTO updateCustomer(CustomerDTO theCustomerDTO, Long customerAFM);
}
