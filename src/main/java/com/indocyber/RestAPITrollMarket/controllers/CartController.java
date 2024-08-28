package com.indocyber.RestAPITrollMarket.controllers;

import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentRequestItemDto;
import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentResponseItemDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.CartRequestDeleteDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.CartRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.CartRowResponseDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.ResponseToCart;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderResponseDto;
import com.indocyber.RestAPITrollMarket.dtos.product.ProductResponseDto;
import com.indocyber.RestAPITrollMarket.models.CartId;
import com.indocyber.RestAPITrollMarket.services.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public  ResponseEntity<List<CartRowResponseDto>> getAll(){
        return  ResponseEntity.ok(cartService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<List<CartRowResponseDto>> getAllById (@PathVariable Integer id) {
        return ResponseEntity.ok(cartService.getAllById(id));
    }

    @PostMapping("")
    public ResponseEntity<ResponseToCart> insert(@Valid @RequestBody CartRequestDto dto) {

        return ResponseEntity.ok(cartService.save(dto));
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<CartRowResponseDto> delete (@RequestBody CartRequestDto dto) {
        return ResponseEntity.ok(cartService.delete(dto));
    }



}
