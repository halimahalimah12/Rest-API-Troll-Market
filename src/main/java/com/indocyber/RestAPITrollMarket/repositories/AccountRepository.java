package com.indocyber.RestAPITrollMarket.repositories;

import com.indocyber.RestAPITrollMarket.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
