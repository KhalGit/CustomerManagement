package com.example.customermanagement.entity;

import com.example.customermanagement.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")

public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                                                        //variables of orders entity

    @ManyToOne                                                                              //relation between orders-customers
    @JoinColumn(name = "customer_id")                                                       //foreign key
    private Customer customer;

    @ManyToOne                                                                              //relation between orders-contacts
    @JoinColumn(name = "contact_id")                                                        //foreign key
    private Contact contact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @Column(name = "orderDate")                                                             //columns of orders entity
    private Date orderDate = new Date();

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "notes")
    private String notes;

    public Order() {                                                       //constructor, getters and setters
    }

    public Order(OrderDTO orderDTO) {                       //copying all properties
        BeanUtils.copyProperties(orderDTO, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
}