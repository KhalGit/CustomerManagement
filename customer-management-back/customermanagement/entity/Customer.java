package com.example.customermanagement.entity;

import com.example.customermanagement.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")

public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;                                        //Variables of Customers

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "AFM")
    private Long AFM;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @Column(name = "dateCreated")
    private Date dateCreated = new Date();

    @OneToMany(mappedBy = "customers",                                  //relation between customers-orders
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)          //lazy fetchtype for faster loading
    private List<Order> customersOrderList = new ArrayList<>();

    @OneToMany(mappedBy = "customers",                                  //relation between customers-orders
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)          //lazy fetchtype for faster loading
    private List<Contact> customersContactList = new ArrayList<>();

    public Customer(){                                                 //constructor, getters and setters
    }

    public Customer(Long Id){
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getAFM(){
        return AFM;
    }

    public void setAFM(Long AFM) {
        this.AFM = AFM;
    }

    public Long getPhone(){
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate(){
        return dateCreated;
    }

    public void setDate(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Order> getCustomersOrderList() {
        return customersOrderList;
    }

    public void setCustomersOrderList(List<Order> orderList) {
        this.customersOrderList = orderList;
    }

    public List<Contact> getCustomersContactList() {
        return customersContactList;
    }

    public void setCustomersContactList(List<Contact> contactList) {
        this.customersContactList = contactList;
    }

    public Customer(CustomerDTO customerDTO) {                                                    //copying all properties except customersOrdersList and customersContactList
        BeanUtils.copyProperties(customerDTO, this, "customersOrderList, customersContactList");    //because it is copied separately in addOrder method
    }                                                                                                //as it is a list and its copy is more complicated

    public void addOrder(Order tempOrder) {                                    //adding data in customersOrdersList
        if (customersOrderList == null) {
            customersOrderList = new ArrayList<>();
        }

        customersOrderList.add(tempOrder);

        tempOrder.setCustomer(this);
    }

    public void addContact(Contact tempContact) {
        if (customersContactList == null)  {
            customersContactList = new ArrayList<>();
        }

        customersContactList.add(tempContact);

        tempContact.setCustomer(this);
    }
}
