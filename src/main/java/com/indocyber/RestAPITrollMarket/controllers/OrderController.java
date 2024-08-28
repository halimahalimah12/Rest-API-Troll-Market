package com.indocyber.RestAPITrollMarket.controllers;

import com.indocyber.RestAPITrollMarket.dtos.order.OrderResponseDto;
import com.indocyber.RestAPITrollMarket.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponseDto>> getAllOrder (){
        return ResponseEntity.ok(orderService.getAll());
    }
    @GetMapping("{id}")
    public  ResponseEntity<OrderResponseDto> getAllById(@PathVariable Integer id){
        return  ResponseEntity.ok(orderService.getAllById(id));
    }

}
