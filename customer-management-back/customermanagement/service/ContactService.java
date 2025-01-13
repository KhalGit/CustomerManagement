package com.example.customermanagement.service;

import com.example.customermanagement.dto.ContactDTO;

import java.util.List;

public interface ContactService {

    public List<ContactDTO> findAllContacts();

    public ContactDTO findByContactId(Long contactId);

    public ContactDTO addContact(ContactDTO theContactDTO);

    public void deleteByContactId(Long contactId);

    public ContactDTO updateContact(ContactDTO theContactDTO, Long contactId);
}