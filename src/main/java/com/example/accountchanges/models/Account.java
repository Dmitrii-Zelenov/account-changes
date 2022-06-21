package com.example.accountchanges.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Entity
@Table
@Getter
@Setter
public class Account {
    @Id
    @Size(min = 20, max = 20)
    private String accountNumber;

    private int requestCount = 1;

    @Size(min = 4)
    private String password;

    private BigDecimal sum = new BigDecimal("100.00");

    public void increaseCounter () {
        requestCount += 1;
    }
}
