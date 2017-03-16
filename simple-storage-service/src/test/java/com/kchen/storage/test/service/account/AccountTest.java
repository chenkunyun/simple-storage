package com.kchen.storage.test.service.account;

import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import com.kchen.storage.service.account.AccountService;
import com.kchen.storage.test.service.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kchen on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void loginTest() {
        Assert.assertNull(accountService.login("", ""));
    }

    @Test
    public void addAccount() {
        Account account = new Account();
        account.setEmail("1111111@gmail.com");
        account.setEnabled(1);
        account.setNickName("nickname");
        account.setUserName("admin");
        account.setPassword("123456");
        account.setRole("admin");
        account.setPhone("12345678900");
        accountMapper.insert(account);
    }
}
