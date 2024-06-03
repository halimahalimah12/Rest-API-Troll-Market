package com.indocyber.RestAPITrollMarket.controllers;

import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentResponseItemDto;
import com.indocyber.RestAPITrollMarket.dtos.product.ProductResponseDto;
import com.indocyber.RestAPITrollMarket.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/merchandise")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getById (@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }


    @PutMapping("discontinue/{id}")
    public ResponseEntity<ProductResponseDto> updateIsService (@PathVariable Integer id) {
        return ResponseEntity.ok(productService.discontinue(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponseDto> delete (@PathVariable Integer id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}
