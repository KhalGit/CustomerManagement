package com.example.customermanagement.restControllers;

import com.example.customermanagement.dto.CustomerDTO;
import com.example.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")

public class CustomerRestController {

    @Autowired                                                                      //dependency injection from customerservice
    private CustomerService customerService;

    @GetMapping("/customers")                                                   //show all customers in http request
    public List<CustomerDTO> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/inventory/{AFM}")                                             //show a specific row from customer table with a specific AFM
    public CustomerDTO getCustomer(@PathVariable Long AFM) {
        CustomerDTO theCustomerDTO = customerService.findByCustomerAFM(AFM);

        return theCustomerDTO;
    }

    @PutMapping("/customers/{AFM}")                                                 //edit a row from customer table with a specific AFM
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO theCustomerDTO,
                                       @PathVariable("AFM") Long AFM)  {
        return customerService.updateCustomer(theCustomerDTO, AFM);
    }

    @PostMapping("/customers")                                                          //add a new row in customer table
    public CustomerDTO addCustomer(@Validated @RequestBody CustomerDTO theCustomerDTO)  {
        theCustomerDTO.setId(null);
        return customerService.addCustomer(theCustomerDTO);
    }

    @DeleteMapping("/customer/{AFM}")                                                   //delete a row from customer table with a specific AFM
    public String deleteCustomer(@PathVariable("AFM") Long AFM)  {
        CustomerDTO tempCustomerDTO = customerService.findByCustomerAFM(AFM);

        if (tempCustomerDTO == null)    {                                                   //throw exception if given "customer" is null
            throw new RuntimeException("Customer AFM not found - " + AFM);
        }
        customerService.deleteByCustomerAFM(AFM);

        return "Deleted customer AFM - " + AFM;
    }

}
