package com.kchen.storage.service.account.impl;

import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.domain.common.AccountExample;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import com.kchen.storage.service.account.AccountService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kchen on 2017/3/1.
 * impl
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account login(String loginName, String password) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(loginName)
                .andPasswordEqualTo(password);
        List<Account> accountList = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(accountList)) {
            return null;
        }

        return accountList.get(0);
    }
}
