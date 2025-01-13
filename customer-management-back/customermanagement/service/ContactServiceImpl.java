package com.example.customermanagement.service;

import com.example.customermanagement.dto.ContactDTO;
import com.example.customermanagement.dto.OrderDTO;
import com.example.customermanagement.entity.Contact;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.entity.Order;
import com.example.customermanagement.exception.DataNotFound;
import com.example.customermanagement.repository.ContactRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.validation.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContactValidator contactValidator;


    @Override
    public List<ContactDTO> findAllContacts()  {

        List<Contact> theContacts = contactRepository.findAll();
        List<ContactDTO> theContactsDTO = new ArrayList<>();

        for (Contact c: theContacts)    {                           //for each Contact object create a new DTO
            theContactsDTO.add(new ContactDTO(c));
        }

        if (theContactsDTO.isEmpty())   {                           //if there is no data call DataNotFound exception
            DataNotFound dataNotFound;
        }
        return theContactsDTO;
    }

    @Override
    public ContactDTO findByContactId(Long contactId) {

        contactValidator.idNotFound(contactId);                           //if id is not found throw exception
        Contact theContact = contactRepository.findById(contactId).orElseThrow(ArithmeticException::new);      //if  ArithmeticException is thrown,
                                                                                                                //then contactValidator doesnt work properly
        return new ContactDTO(theContact);
    }

    @Override
    public ContactDTO addContact(ContactDTO theContactDTO)  {
        contactValidator.customerNull(theContactDTO.getCustomerId());                                 //exception for wrong info added by user

        Contact theContact = new Contact(theContactDTO);

        Customer customer = customerRepository.getOne(theContactDTO.getCustomerId());       //finds the customerId, because it is true

        for (OrderDTO o: theContactDTO.getContactsOrdersList()) {                       //creates a new orderList for this Contact
            o.setId(theContact.getId());
            Order order = new Order(o);
            order.setContact(theContact);
            theContact.getContactsOrderList().add(order);
        }

        theContact.setCustomer(customer);                                               //sets customer to this contact, based on given customerId
        theContact = contactRepository.save(theContact);
        return toDTO(theContact);

    }

    @Override
    public void deleteByContactId(Long contactId) {
        contactValidator.idNotFound(contactId);                             //checks contactId validity
        contactRepository.deleteById(contactId);
    }

    @Override
    public ContactDTO updateContact(ContactDTO theContactDTO, Long contactId) {
        contactValidator.idNotFound(contactId);                                         //checks contactId validity
        contactValidator.customerNull(theContactDTO.getCustomerId());                   //checks if customerId is true

        Contact theContact = contactRepository.findById(contactId).get();
        Customer customer = customerRepository.getOne(theContactDTO.getCustomerId());

        theContact.setCustomer(customer);                                               //updates the contact
        theContact = contactRepository.save(theContact);

        return toDTO(theContact);
    }

    private ContactDTO toDTO(Contact contact) {                                 //copies contactsOrderList from the entity to dto
        ContactDTO dto = new ContactDTO(contact);
        for (Order o: contact.getContactsOrderList())   {                       //for-loop to add items in list of orderdto
            dto.getContactsOrdersList().add(new OrderDTO(o));
        }

        return dto;
    }
}
