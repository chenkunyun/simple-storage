package com.kchen.storage.rest.config.security;

import com.google.common.collect.Sets;
import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import com.kchen.storage.service.account.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;


@Configuration
public class RestRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long)principals.getPrimaryPrincipal();
        Account account = accountMapper.selectByPrimaryKey(userId);
        if (account != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            HashSet<String> roles = Sets.newHashSet(account.getRole());
            authorizationInfo.setRoles(roles);
//            authorizationInfo.addStringPermissions(role.getPermissionsName());
            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        Account account = accountService.login(
                usernamePasswordToken.getUsername(),
                new String(usernamePasswordToken.getPassword())
            );
        if (account != null) {
            return new SimpleAuthenticationInfo(account.getId(), account.getPassword(), getName());
        }

        return null;
    }
}
