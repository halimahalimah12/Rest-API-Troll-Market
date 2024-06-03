package com.indocyber.RestAPITrollMarket.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Carts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @EmbeddedId
    private CartId id;
    @Column(name = "Quantity")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shipmentId")
    @JoinColumn(name = "ShipmentId", insertable = false, updatable = false)
    private Shipment shipment;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("buyerId")
    @JoinColumn(name = "BuyerId", insertable = false, updatable = false)
    private Buyer buyer;
}