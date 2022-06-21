package com.example.accountchanges.DAO;

import com.example.accountchanges.models.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Account get(String accountNumber) {
        return entityManager.find(Account.class, accountNumber);
    }

    @Override
    public void save(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void update(String accountNumber, Account updatedAccount) {
        entityManager.createQuery("update Account a set a.sum = :sum, a.requestCount = :count " +
                "where a.accountNumber = :accNum")
                .setParameter("sum", updatedAccount.getSum())
                .setParameter("count", updatedAccount.getRequestCount())
                .setParameter("accNum", accountNumber)
                .executeUpdate();
    }
}
