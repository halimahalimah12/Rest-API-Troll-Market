package com.indocyber.RestAPITrollMarket.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartId implements Serializable {
    @Column(name = "ProductId")
    private Integer productId;
    @Column(name = "ShipmentId")
    private Integer shipmentId;
    @Column(name = "BuyerId")
    private Integer buyerId;
}
