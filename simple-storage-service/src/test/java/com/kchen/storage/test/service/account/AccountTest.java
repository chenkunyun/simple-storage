package com.kchen.storage.test.service.account;

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

    @Test
    public void loginTest() {
        Assert.assertFalse(accountService.login("", ""));
    }
}
