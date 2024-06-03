package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.cart.CartRequestDeleteDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.CartRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.CartRowResponseDto;
import com.indocyber.RestAPITrollMarket.dtos.cart.ResponseToCart;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.order.OrderResponseDto;
import com.indocyber.RestAPITrollMarket.models.*;
import com.indocyber.RestAPITrollMarket.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ShipmentRepository shipmentRepository;
    private  final AccountRepository accountRepository;


    public CartService(CartRepository cartRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository, ProductRepository productRepository, ShipmentRepository shipmentRepository, AccountRepository accountRepository) {
        this.cartRepository = cartRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.shipmentRepository = shipmentRepository;
        this.accountRepository = accountRepository;
    }

    public List<CartRowResponseDto> getAllById(Integer id) {
        List<Cart> cartList = cartRepository.findAllByBuyer(id);
        List<CartRowResponseDto> cartRowResponseDtoList = cartList.stream()
                .map(this::convertResponseDto
                ).toList();
        return cartRowResponseDtoList;
    }

    private Double totalPrice(Double unitPrice,Integer quantity, Double priceShip){
        return (unitPrice* quantity)+priceShip;
    }

    public ResponseToCart save(CartRequestDto dto) {

        Buyer buyer = findBuyer(dto.getBuyerId());
        Product product1 = productRepository.findById(dto.getProductId()).orElseThrow(()->new IllegalArgumentException("not found"));
        Seller seller = sellerRepository.sellerFindByAccound(product1.getSeller().getAccount().getUsername());

        if (buyer.getAccount().getUsername().equals(seller.getAccount().getUsername())){
            return ResponseToCart.builder()
                    .pesan("Buyer tidak bisa membeli barang sendiri!")
                    .isSuccess(false)
                    .build();
        }else {
            Product product = findProduct(dto.getProductId());
            Shipment shipment = findShipment(dto.getShipmentId());
            CartId cartId = CartId.builder()
                    .shipmentId(dto.getShipmentId())
                    .productId(dto.getProductId())
                    .buyerId(dto.getBuyerId())
                    .build();

            var cart = Cart.builder()
                    .id(cartId)
                    .quantity(dto.getQuantity())
                    .product(product)
                    .shipment(shipment)
                    .buyer(buyer)
                    .build();
            cart = cartRepository.save(cart);

            return ResponseToCart.builder()
                    .isSuccess(true)
                    .pesan("Successfully Add To Cart")
                    .build();
        }


    }

    private CartRowResponseDto convertResponseDto(Cart cart) {
        return CartRowResponseDto.builder()
                .cartId(cart.getId())
                .nameProduct(cart.getProduct().getName())
                .quantity(cart.getQuantity())
                .shipment(cart.getShipment().getName())
                .seller(cart.getProduct().getSeller().getName())
                .totalPrice(totalPrice(cart.getProduct().getPrice(),cart.getQuantity(),cart.getShipment().getPrice()))
                .build();
    }

    private Buyer findBuyer(Integer id) {
        return buyerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id buyer not found"));
    }
    private Cart findCart(CartId id) {
        return cartRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id cart not found"));
    }
    private Product findProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id product not found"));
    }
    private Shipment findShipment(Integer id){
        return shipmentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id shipment not found"));
    }

    public CartRowResponseDto delete(CartRequestDto dto) {
        CartId cartId = CartId.builder()
                .shipmentId(dto.getShipmentId())
                .buyerId(dto.getBuyerId())
                .productId(dto.getProductId())
                .build();
        Cart cart = findCart(cartId);
        cartRepository.delete(cart);
        return convertResponseDto(cart);
    }

}
