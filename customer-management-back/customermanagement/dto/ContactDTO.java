package com.example.customermanagement.dto;

import com.example.customermanagement.entity.Contact;
import com.example.customermanagement.entity.Order;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data

public class ContactDTO {

    private Long id;                                                            //variables of contactsdto
    private Long customerId;
    private List<OrderDTO> contactsOrdersList = new ArrayList<>();
    private String contactName;
    private Long phone;
    private String email;
    private String relation;

    public ContactDTO()   {                                                    //constructor, getters and setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderDTO> getContactsOrdersList() {
        return contactsOrdersList;
    }

    public void setContactsOrdersList(List<OrderDTO> orders) {
        this.contactsOrdersList = orders;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public ContactDTO(Contact contact)   {                                       //copying list for orderlist
        BeanUtils.copyProperties(contact, this, "orders");

        for (Order o: contact.getContactsOrderList())    {
            contactsOrdersList.add(new OrderDTO(o));
        }
    }
}