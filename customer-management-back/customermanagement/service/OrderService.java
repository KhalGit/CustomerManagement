package com.example.customermanagement.service;

import com.example.customermanagement.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO>  findAllOrders();

    public OrderDTO findByOrderId(Long orderId);

    public OrderDTO addOrder(OrderDTO theOrderDTO);

    public void deleteByOrderId(Long orderId);

    public OrderDTO updateOrder(OrderDTO theOrderDTO, Long orderId);
}
