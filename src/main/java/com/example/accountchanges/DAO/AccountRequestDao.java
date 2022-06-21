package com.example.accountchanges.DAO;

import com.example.accountchanges.models.AccountRequest;


import java.math.BigInteger;
import java.util.List;

public interface AccountRequestDao {
    public List<AccountRequest> getAllRequestsByAccount (String accountNumber);
    public AccountRequest add (AccountRequest accountRequest);
}
