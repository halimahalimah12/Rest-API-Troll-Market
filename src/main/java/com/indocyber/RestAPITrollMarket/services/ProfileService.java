package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.profile.ProfileRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.profile.ProfileResponseDto;
import com.indocyber.RestAPITrollMarket.models.Buyer;
import com.indocyber.RestAPITrollMarket.repositories.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private BuyerRepository buyerRepository;

    public ProfileService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public ProfileResponseDto addBalace(ProfileRequestDto dto){
        Buyer buyer = buyerRepository.findById(dto.getId())
                .orElseThrow(()->new IllegalArgumentException("ID not Found"));
        buyer.setBalance(buyer.getBalance()+dto.getBalance());
        buyer = buyerRepository.save(buyer);
        return ProfileResponseDto.builder().balance(buyer.getBalance()).build();
    }
}
