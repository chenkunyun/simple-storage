package com.kchen.storage.rest.config.security;

import com.google.common.collect.Sets;
import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import com.kchen.storage.service.account.AccountService;
import org.apache.commons.lang3.ClassUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;


@Configuration
public class RestRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostConstruct
    public void credentialsMatcher() {
        super.setCredentialsMatcher(new RestCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long)principals.getPrimaryPrincipal();
        Account account = accountMapper.selectByPrimaryKey(userId);
        if (account != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            HashSet<String> roles = Sets.newHashSet(account.getRole());
            authorizationInfo.setRoles(roles);
            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        Account account = accountService.login(
                usernamePasswordToken.getUsername()
        );
        if (account != null) {
            return new SimpleAuthenticationInfo(
                    account.getId(),
                    account.getPassword(),
                    ByteSource.Util.bytes(account.getSalt()),
                    getName());
        }

        return null;
    }
}
