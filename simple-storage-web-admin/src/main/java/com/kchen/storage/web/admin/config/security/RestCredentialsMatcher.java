package com.kchen.storage.web.admin.config.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.security.MessageDigest;

public class RestCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        SimpleAuthenticationInfo simpleAuthenticationInfo = (SimpleAuthenticationInfo) info;

        MessageDigest sha256Digest = DigestUtils.getSha256Digest();
        sha256Digest.update(simpleAuthenticationInfo.getCredentialsSalt().getBytes());
        byte[] tokenCredentials = sha256Digest.digest(new String(usernamePasswordToken.getPassword()).getBytes());
        Object accountCredentials = simpleAuthenticationInfo.getCredentials();
        return super.equals(tokenCredentials, accountCredentials);
    }
}
