package com.kchen.storage.test.dao.account;

import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import com.kchen.storage.test.dao.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountTest {

    @Autowired
    private AccountMapper mapper;

    @Test
    public void accountTest() {
        Account account = mapper.selectByPrimaryKey(Long.valueOf(-1));
        assertNull(account);
    }
}
