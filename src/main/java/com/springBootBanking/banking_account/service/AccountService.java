package com.springBootBanking.banking_account.service;

import com.springBootBanking.banking_account.dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);

    AccountDto deposit(long id, double amount);
    AccountDto withdraw(long id, double amount);

    List<AccountDto> getAllAccount();
    void deleteAccount(long id);
}
