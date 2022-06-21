package com.example.accountchanges.DAO;

import com.example.accountchanges.models.AccountRequest;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@Repository
public class AccountRequestDaoImpl implements AccountRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountRequest> getAllRequestsByAccount(String accountNumber) {
        return entityManager.createQuery("select ar from AccountRequest ar where ar.account.accountNumber =: accountNumber")
                .setParameter("accountNumber", accountNumber)
                .getResultList();
    }

    @Override
    public AccountRequest add(AccountRequest accountRequest) {
        entityManager.persist(accountRequest);
        return accountRequest;
    }
}
