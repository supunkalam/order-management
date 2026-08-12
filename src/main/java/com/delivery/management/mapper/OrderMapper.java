package com.delivery.management.mapper;

import com.delivery.management.model.Order;
import com.delivery.management.request.OrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest request);
}