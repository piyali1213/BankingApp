package com.springBootBanking.banking_account.mapper;

import com.springBootBanking.banking_account.dto.AccountDto;
import com.springBootBanking.banking_account.entity.Account;

public class AccountMapper {
    public static Account maptoAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }
    public static AccountDto mapToAccountDto(Account account)
    {
        AccountDto accountDto=new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
