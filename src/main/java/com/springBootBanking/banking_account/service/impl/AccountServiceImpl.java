package com.springBootBanking.banking_account.service.impl;

import com.springBootBanking.banking_account.dto.AccountDto;
import com.springBootBanking.banking_account.entity.Account;
import com.springBootBanking.banking_account.mapper.AccountMapper;
import com.springBootBanking.banking_account.repository.AccountRepository;
import com.springBootBanking.banking_account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

@Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //create the object of account class
       Account account = AccountMapper.maptoAccount(accountDto);

       //accountRepository has the method save
        // call save method to call account jpa entity and pass account save entity to this save method
        // local variable savedAccount
       Account savedAccount =accountRepository.save(account);

       // now the return type is accountDto so convert to savedAccount to AccountDto my calling AccountMpper
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account dos not exit"));
        return AccountMapper.mapToAccountDto(account);
    }
// first get the account and then deposit
    @Override
    public AccountDto deposit(long id, double amount) {
        Account account= accountRepository.
                findById(id).
                orElseThrow(()->new RuntimeException("Account does not exit"));

     double total= account.getBalance() + amount;
     account.setBalance(total);
     Account savedAccount= accountRepository.save(account);

     return AccountMapper.mapToAccountDto(savedAccount);
    }
    // first get the account and then withdraw

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account= accountRepository.
                findById(id).
                orElseThrow(()->new RuntimeException("Account does not exit"));

        if(account.getBalance()< amount)
        {
            throw new RuntimeException("insufficient balance");
        }

        double total= account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }
// get all account list
    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts= accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }
// delete account
    @Override
    public void deleteAccount(long id) {
        Account account= accountRepository.
                findById(id).
                orElseThrow(()->new RuntimeException("Account does not exit"));
        accountRepository.deleteById(id);



    }
}
