package com.delivery.management.service;

import com.delivery.management.mapper.OrderMapper;
import com.delivery.management.model.Order;
import com.delivery.management.repository.OrderRepository;
import com.delivery.management.request.OrderRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
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

    public String placeOrder(OrderRequest orderRequest){
        Order savedOrder = orderRepository.save(createOrder(orderRequest));
        return "Order placed successfully with ID: " + savedOrder.getOrderNumber();
    }

    private Order createOrder(OrderRequest request) {
        return orderMapper.toEntity(request);
    }
}