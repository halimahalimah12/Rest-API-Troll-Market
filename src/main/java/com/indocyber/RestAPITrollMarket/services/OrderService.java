package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.order.OrderRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderResponseDto;
import com.indocyber.RestAPITrollMarket.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
