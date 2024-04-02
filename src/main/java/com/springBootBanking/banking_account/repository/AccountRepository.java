package com.springBootBanking.banking_account.repository;

import com.springBootBanking.banking_account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
