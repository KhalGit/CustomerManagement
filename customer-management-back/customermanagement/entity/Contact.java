package com.example.customermanagement.entity;

import com.example.customermanagement.dto.ContactDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacts")

public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST,                                      //relation between customers-contacts
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH} )           //cant remove the parent entity
    @JoinColumn(name = "customer_id")                                               //foreign key
    private Customer customer;

    @OneToMany(mappedBy = "contacts",                                               //relation between contacts-orders
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)          //lazy fetchtype for faster loading
    private List<Order> contactsOrderList = new ArrayList<>();

    @Column(name = "contactName")                                       //columns of contacts table
    private String contactName;                                         //variables of contacts entity

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "relation")
    private String relation;

    public Contact(){                                                  //constructor, getters and setters
    }

    public Contact(Long Id){
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<Order> getContactsOrderList() {
        return contactsOrderList;
    }

    public void setContactsOrderList(List<Order> contactsOrderList) {
        this.contactsOrderList = contactsOrderList;
    }

    public Contact(ContactDTO contactDTO)    {                                                   //copying all properties except the contactssOrdersList
        BeanUtils.copyProperties(contactDTO, this, "contactsOrderList");     //because it is copied separately in addOrder method
    }                                                                                               //as it is a list and its copy is more complicated

    public void addOrder(Order tempOrder) {                                    //adding data in contactsOrdersList
        if (contactsOrderList == null) {
            contactsOrderList = new ArrayList<>();
        }

        contactsOrderList.add(tempOrder);
        tempOrder.setContact(this);
    }

}
