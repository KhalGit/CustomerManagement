package com.example.customermanagement.dto;

import com.example.customermanagement.entity.Contact;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class CustomerDTO {

    private Long id;                                                        //variables of customersDTO
    private String lastName;
    private String firstName;
    private Long AFM;
    private Long phone;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date dateCreated = new Date();
    private List<OrderDTO> customersOrderList = new ArrayList<>();
    private List<ContactDTO> customersContactList = new ArrayList<>();

    public CustomerDTO()   {                                               //constructor, getters and setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getAFM() {
        return AFM;
    }

    public void setAFM(Long AFM) {
        this.AFM = AFM;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderDTO> getCustomersOrderList() {
        return customersOrderList;
    }

    public void setCustomersOrderList(List<OrderDTO> customersOrderList) {
        this.customersOrderList = customersOrderList;
    }

    public List<ContactDTO> getCustomersContactList()   {
        return customersContactList;
    }

    public void setCustomersContactList(List<ContactDTO> customersContactList) {
        this.customersContactList = customersContactList;
    }

    public CustomerDTO(Customer customer) {                                   //copying all details from ordersList
        BeanUtils.copyProperties(customer, this, "orders, contacts"); //create new ordersDTO for each one
        if (customer.getCustomersOrderList() != null)   {                               //and added to the list
            for (Order o : customer.getCustomersOrderList())   {                       // "o" comes from o-rders
                this.customersOrderList.add(new OrderDTO(o));
            }
        }
        if (customer.getCustomersContactList() != null) {
            for (Contact c: customer.getCustomersContactList()) {
                this.customersContactList.add(new ContactDTO(c));
            }
        }
    }
}