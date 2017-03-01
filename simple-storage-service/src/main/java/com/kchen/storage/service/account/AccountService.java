package com.kchen.storage.service.account;

/**
 * Created by kchen on 2017/3/1.
 * handling account stuff
 */
public interface AccountService {
    boolean login(String loginName, String password);
}
