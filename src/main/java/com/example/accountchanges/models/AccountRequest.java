package com.example.accountchanges.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Entity
@Table
@Setter
@Getter
public class AccountRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Account account;

    @Column(name = "request_sum")
    @Size(min = -50000, max = 50000)
    private BigDecimal requestSum;

}
