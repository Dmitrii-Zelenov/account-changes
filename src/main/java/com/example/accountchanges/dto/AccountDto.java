package com.example.accountchanges.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    private BigInteger accountNumber;
    private String password;
    private BigDecimal amount;
//    private String timeZone;
}
