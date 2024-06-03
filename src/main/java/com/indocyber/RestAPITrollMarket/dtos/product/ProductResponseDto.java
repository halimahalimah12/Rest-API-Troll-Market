package com.indocyber.RestAPITrollMarket.dtos.product;

import com.indocyber.RestAPITrollMarket.models.Category;
import com.indocyber.RestAPITrollMarket.models.Seller;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private Integer id;
    private String seller;
    private String category;
    private String name;
    private Double price;
    private String description;
    private String discontinue;
}
