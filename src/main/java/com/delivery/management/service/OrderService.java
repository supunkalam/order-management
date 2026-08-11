package com.delivery.management.service;

import com.delivery.management.model.Order;
import com.delivery.management.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Cacheable(value = "orderDetails", key = "#orderNumber")
    public String getOrderByNumber(long orderNumber) {
        try{
            log.info("Fetching from DB for orderNumber= {} " , orderNumber);
            Order savedOrder = orderRepository.getReferenceById(orderNumber);
            return savedOrder.getCustomerName();
        }
        catch (EntityNotFoundException e){
            return "No order found for the order with order number " + orderNumber;
        }
    }
}