package com.example.customermanagement.restControllers;

import com.example.customermanagement.dto.OrderDTO;
import com.example.customermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")

public class OrderRestController {

    @Autowired                                                          //dependency injection from orderservice
    private OrderService orderService;

    @GetMapping("/order")                                           //show all orders in http request
    public List<OrderDTO> findAllOrders()   {
        return orderService.findAllOrders();
    }

    @GetMapping("/order/{id}")                                      //show a specific row from order table with a specific id
    public OrderDTO getOrder(@PathVariable Long id) {                       //@pathvariable for the appropriate variable in the url
        OrderDTO theOrderDTO = orderService.findByOrderId(id);

        return theOrderDTO;
    }

    @PutMapping("/order/{id}")                                                          //edit a row from order table with a specific id
    public OrderDTO updateOrder(@RequestBody OrderDTO theOrderDTO, @PathVariable("id") Long orderId) {  //@requestbody for mapped values with the created object
        return orderService.updateOrder(theOrderDTO, orderId);
    }

    @PostMapping("/order")                                                          //add a new row in order table
    public OrderDTO addOrder(@Validated @RequestBody OrderDTO theOrderDTO)  {                   //@validated for group and partial validation
        theOrderDTO.setId(null);
        return orderService.addOrder(theOrderDTO);
    }

    @DeleteMapping("/order/{id}")                                               //delete a row from order table with a specific id
    public String deleteOrder(@PathVariable("id") Long orderId)  {
        OrderDTO tempOrderDTO = orderService.findByOrderId(orderId);

        if (tempOrderDTO == null)   {                                                   //throw exception if given "order" is null
            throw new RuntimeException("OrderId " + orderId + " not found");
        }

        orderService.deleteByOrderId(orderId);

        return "Deleted order orderId - " + orderId;
    }

}
