package com.delivery.management.controller;

import com.delivery.management.request.OrderRequest;
import com.delivery.management.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orderManagement")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderDetails/{orderNumber}")
    public String getOrderDetails(@PathVariable long orderNumber){
        return orderService.getOrderByNumber(orderNumber);
    }


    @PostMapping("/placeOrder")
    public String placeOrder(OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }
}
