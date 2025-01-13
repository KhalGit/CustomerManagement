package com.example.customermanagement.dto;

import com.example.customermanagement.entity.Order;
import com.example.customermanagement.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data

public class OrderDTO {

    private Long id;                                                            //variables of oredrsdto
    private Long customerId;
    private Long contactId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date = new Date();
    private Long amount;
    private OrderStatus orderStatus;
    private String notes;

    public OrderDTO()  {                                                       //constructor, getters and setters
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

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderDTO(Order order) {                                               //copying IDs from foreign keys
        BeanUtils.copyProperties(order, this);

        this.customerId = order.getCustomer().getId();
        this.contactId = order.getContact().getId();
    }
}
