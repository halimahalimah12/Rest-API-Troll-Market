package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.product.ProductResponseDto;
import com.indocyber.RestAPITrollMarket.models.Product;
import com.indocyber.RestAPITrollMarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto discontinue(Integer id) {
        Product product = findProduct(id);
        product.setDiscontinue(true);
        product = productRepository.save(product);
        return convertResponeDto(product);
    }

    private ProductResponseDto convertResponeDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .seller(product.getSeller().getName())
                .category(product.getCategory().getName())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription() == null || product.getDescription().isBlank() ? "-":product.getDescription())
                .discontinue(product.getDiscontinue() == true ? "Yes" : "No")
                .build();
    }

    public ProductResponseDto delete(Integer id) {
        Product product = findProduct(id);
        productRepository.deleteById(id);
        return convertResponeDto(product);
    }

    private Product findProduct(Integer id){
        return productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("ID not found"));
    }

    public ProductResponseDto getById(Integer id) {
        Product product = findProduct(id);
        return convertResponeDto(product);
    }
}
