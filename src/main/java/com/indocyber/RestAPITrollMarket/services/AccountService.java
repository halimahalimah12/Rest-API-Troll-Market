package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.auth.SelectListRoleDto;
import com.indocyber.RestAPITrollMarket.models.Buyer;
import com.indocyber.RestAPITrollMarket.models.Seller;
import com.indocyber.RestAPITrollMarket.repositories.AccountRepository;
import com.indocyber.RestAPITrollMarket.repositories.BuyerRepository;
import com.indocyber.RestAPITrollMarket.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    public AccountService(AccountRepository accountRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.accountRepository = accountRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    public List<SelectListRoleDto> getRoleDropdown(String username) {

        List<SelectListRoleDto> roleList = new ArrayList<>();

        Buyer buyer = buyerRepository.buyerFindByAccound(username);
        if (buyer != null) {
            roleList.add(SelectListRoleDto.builder()
                    .roleName("Buyer")
                    .build());
        }

        Seller seller = sellerRepository.sellerFindByAccound(username);
        if (seller != null) {
            roleList.add(SelectListRoleDto.builder()
                    .roleName("Seller")
                    .build());
        }

        if (seller == null && buyer == null) {
            roleList.add(SelectListRoleDto.builder()
                    .roleName("Admin")
                    .build());
        }

        return roleList;
    }
}
