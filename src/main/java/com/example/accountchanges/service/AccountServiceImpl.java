package com.example.accountchanges.service;

import com.example.accountchanges.DAO.AccountDao;
import com.example.accountchanges.DAO.AccountRequestDao;
import com.example.accountchanges.dto.AccountDto;
import com.example.accountchanges.dto.AccountResponseDto;
import com.example.accountchanges.models.Account;
import com.example.accountchanges.models.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    private final AccountDao accountDao;
    private final AccountRequestDao accountRequestDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao, AccountRequestDao accountRequestDao) {
        this.accountDao = accountDao;
        this.accountRequestDao = accountRequestDao;
    }

    @Override
    public AccountResponseDto accountTransaction(AccountDto accountDto) {
//        ZonedDateTime zonedDateTime;
//        if (accountDto.getTimeZone() == null) {
//            zonedDateTime = ZonedDateTime.now();
//        }
//        zonedDateTime = ZonedDateTime.now(ZoneId.of(accountDto.getTimeZone()));
        Account account = accountDao.get(accountDto.getAccountNumber().toString());
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        if (account == null) {
            account = new Account();
            accountResponseDto.setAmountBefore(account.getSum());

            account.setAccountNumber(accountDto.getAccountNumber().toString());
            account.setPassword(accountDto.getPassword());
            account.setSum( (account.getSum().add(accountDto.getAmount()).setScale(2)).setScale(2) );
            account.increaseCounter();
            accountDao.save(account);

            AccountRequest accountRequest = new AccountRequest();
            accountRequest.setAccount(account);
            accountRequest.setRequestSum(accountDto.getAmount().setScale(2, RoundingMode.UP));

            accountRequestDao.add(accountRequest);

            accountResponseDto.setRequestId(accountRequest.getId());
            accountResponseDto.setAccountNumber(new BigInteger(account.getAccountNumber()) );
            accountResponseDto.setAmount(accountDto.getAmount());
            accountResponseDto.setAmountAfter(account.getSum());
            accountResponseDto.setChange(
                    (( accountResponseDto.getAmountAfter()
                            .subtract(accountResponseDto.getAmountBefore()) )
                            .divide(accountResponseDto.getAmountBefore(), 2, RoundingMode.UP)
                            .multiply(new BigDecimal(100)) ).setScale(2, RoundingMode.UP)
                    );
            accountResponseDto.setAvg(  account.getSum().divide(new BigDecimal(account.getRequestCount()), 2, RoundingMode.UP));

            return accountResponseDto;
        } else {
            if (!account.getPassword().equals(accountDto.getPassword())) {
                throw new IllegalArgumentException("Некорректно введен пароль");
            }


            accountResponseDto.setAmountBefore(account.getSum());

            AccountRequest accountRequest = new AccountRequest();
            accountRequest.setAccount(account);
            accountRequest.setRequestSum(accountDto.getAmount());
            accountRequestDao.add(accountRequest);

            account.increaseCounter();
            account.setSum(account.getSum().add(accountDto.getAmount()));
            accountDao.update(accountDto.getAccountNumber().toString(), account);

            accountResponseDto.setRequestId(accountRequest.getId());
            accountResponseDto.setAccountNumber(new BigInteger(account.getAccountNumber()));
            accountResponseDto.setAmountAfter(account.getSum());
            accountResponseDto.setAmount(accountDto.getAmount());

            BigDecimal amountAfter = accountResponseDto.getAmountAfter();
            BigDecimal amountBefore = accountResponseDto.getAmountBefore();

            BigDecimal change = amountAfter.subtract(amountBefore).divide(amountBefore, 2, RoundingMode.UP).multiply(new BigDecimal(100));

            accountResponseDto.setChange(change);
            accountResponseDto.setAvg(  account.getSum()
                    .divide(new BigDecimal(account.getRequestCount()), 2, RoundingMode.UP));

            return accountResponseDto;
        }
    }
}
