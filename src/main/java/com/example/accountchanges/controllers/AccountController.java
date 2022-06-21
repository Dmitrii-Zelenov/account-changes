package com.example.accountchanges.controllers;

import com.example.accountchanges.dto.AccountResponseDto;
import com.example.accountchanges.service.AccountService;
import com.example.accountchanges.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<?> accountTransaction (@RequestBody AccountDto accountDto) {
        try {
            return ResponseEntity.ok(accountService.accountTransaction(accountDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public String hello () {
        return "Hello";
    }
}
