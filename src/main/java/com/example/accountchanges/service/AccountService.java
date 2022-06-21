package com.example.accountchanges.service;

import com.example.accountchanges.dto.AccountDto;
import com.example.accountchanges.dto.AccountResponseDto;

public interface AccountService {
    public AccountResponseDto accountTransaction (AccountDto accountDto);
}
