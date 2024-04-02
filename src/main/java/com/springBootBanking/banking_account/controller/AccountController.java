package com.springBootBanking.banking_account.controller;

import com.springBootBanking.banking_account.dto.AccountDto;
import com.springBootBanking.banking_account.entity.Account;
import com.springBootBanking.banking_account.repository.AccountRepository;
import com.springBootBanking.banking_account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get account rest Apid
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto= accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);

    }
    //deposit account Rest Api
@PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request)
    {
        double amount= request.get("amount");
   AccountDto accountDto= accountService.deposit(id,amount);
   return ResponseEntity.ok(accountDto);

    }
    // withdraw rest Api
@PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request)
    {
        double amount =request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //get All Account RestApi
    @GetMapping
public ResponseEntity<List<AccountDto>> getAllAccounts()
{
    List<AccountDto> accounts= accountService.getAllAccount();
    return ResponseEntity.ok(accounts);
}
// delete account Rest Api
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteAccount(@PathVariable Long id)
{
    accountService.deleteAccount(id);
    return ResponseEntity.ok("account Successfully Deleted!");
}


}
