package com.delivery.management.controller;

import com.delivery.management.model.Order;
import com.delivery.management.repository.OrderRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orderManagement")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orderDetails/{orderNumber}")
    public String getOrderDetails(@PathVariable long orderNumber){
        try{
            Order savedOrder = orderRepository.getReferenceById(orderNumber);
            return savedOrder.getCustomerName();
        }
        catch (EntityNotFoundException e){
            return "No order found for the order with order number " + orderNumber;
        }
    }

    @PostMapping("/placeOrder")
    public String placeOrder(Order orderDetails){
        Order savedOrder = orderRepository.save(orderDetails);
        return "Order placed successfully with ID: " + savedOrder.getOrderNumber();
    }
}
