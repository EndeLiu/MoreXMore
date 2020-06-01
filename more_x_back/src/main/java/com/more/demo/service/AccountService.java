package com.more.demo.service;

import com.more.demo.dao.AccountDao;
import com.more.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public List<Account> getAll() {
        List<Account> accountList = accountDao.findAll();
        return accountList;
    }

    public void updateAccount (Account account) {
        accountDao.save(account);
    }

}
