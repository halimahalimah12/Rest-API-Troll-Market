package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.order.OrderRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderResponseDto;
import com.indocyber.RestAPITrollMarket.models.Order;
import com.indocyber.RestAPITrollMarket.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponseDto> getAll(){
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map(this::convertResponseDto)
                .toList();

    }

    public OrderResponseDto getAllById(Integer id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Order ID Not Found"));
        return convertResponseDto(order);
    }


    public OrderResponseDto convertResponseDto(Order order){
        return OrderResponseDto.builder()
                .date(order.getOrderDate())
                .seller(order.getProduct().getSeller().getName())
                .buyyer(order.getBuyer().getName())
                .product(order.getProduct().getName())
                .quantity(order.getQuantity().toString())
                .shipment(order.getShipment().getName())
                .totalPrice(totalPrice(order.getUnitPrice(),order.getQuantity(),order.getShipment().getPrice()).toString())
                .build();
    }

    public Double totalPrice(Double price,Integer quantity, Double priceShip){
        return (price*quantity)+priceShip;
    }

}
