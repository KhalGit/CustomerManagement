package com.example.customermanagement.restControllers;

import com.example.customermanagement.dto.ContactDTO;
import com.example.customermanagement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")

public class ContactRestController {

    @Autowired                                                                      //dependency injection from contactservice
    private ContactService contactService;

    @GetMapping("/contact")                                                   //show all contacts in http request
    public List<ContactDTO> findAllContacts(){
        return contactService.findAllContacts();
    }

    @GetMapping("/inventory/{id}")                                             //show a specific row from contact table with a specific id
    public ContactDTO getcontact(@PathVariable Long id) {
        ContactDTO theContactDTO = contactService.findByContactId(id);

        return theContactDTO;
    }

    @PutMapping("/contact/{id}")                                                 //edit a row from contact table with a specific id
    public ContactDTO updateContact(@RequestBody ContactDTO theContactDTO,
                                      @PathVariable("id") Long id)  {
        return contactService.updateContact(theContactDTO, id);
    }

    @PostMapping("/contact")                                                          //add a new row in contact table
    public ContactDTO addcontact(@Validated @RequestBody ContactDTO theContactDTO)  {
        theContactDTO.setId(null);
        return contactService.addContact(theContactDTO);
    }

    @DeleteMapping("/contact/{id}")                                                   //delete a row from contact table with a specific id
    public String deleteContact(@PathVariable("id") Long id)  {
        ContactDTO tempContactDTO = contactService.findByContactId(id);

        if (tempContactDTO == null)    {                                                   //throw exception if given "id" is null
            throw new RuntimeException("contact id not found - " + id);
        }
        contactService.deleteByContactId(id);

        return "Deleted contact Id - " + id;
    }
    
}