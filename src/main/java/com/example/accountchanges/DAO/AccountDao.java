package com.example.accountchanges.DAO;

import com.example.accountchanges.models.Account;

import java.math.BigInteger;

public interface AccountDao {
    public Account get (String accountNumber);
    public void save (Account account);
    public void update (String accountNumber, Account updatedAccount);

}
