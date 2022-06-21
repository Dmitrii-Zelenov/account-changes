package com.example.accountchanges.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Getter
@Setter
public class AccountResponseDto {
    private Long requestId;
    private BigInteger accountNumber;
    private BigDecimal amount;
    private BigDecimal amountBefore;
    private BigDecimal amountAfter;
    private BigDecimal change;
    private BigDecimal avg;
//    private ZonedDateTime zonedDateTime;
}
