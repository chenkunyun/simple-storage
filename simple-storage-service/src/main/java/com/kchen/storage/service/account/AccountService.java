package com.kchen.storage.service.account;

import com.kchen.storage.dao.domain.common.Account;

/**
 * handling account stuff
 */
public interface AccountService {
    Account login(String loginName);
}
