package com.pop.shiro.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Common-Utils
 * @description: 凭证
 * 密码重试穿次数限制
 * 如果在1个小时内密码最多设置5次，如果尝试次数超过
 * 5次，限制一个小时，如果还是失败，锁定1天，防止密码暴力
 * 破解，通过继承HashCredentialsMatcher，且使用Ehcache
 * 来存储重试的次数和超时的时间
 * @author: 范凌轩
 * @create: 2019-05-14 17:42
 **/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
      CacheManager cacheManager= CacheManager.newInstance(
              CacheManager.class.getClassLoader().getResource("ehcache.xml")
        );
      passwordRetryCache =
              cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username= (String) token.getPrincipal();

        //是否有缓存 尝试的次数 用username作为key来存储次数
        Element element = passwordRetryCache.get(username);
        if(null==element){
            element = new Element(username,new AtomicInteger(0));
            passwordRetryCache.put(element);
        }

        AtomicInteger retryCount = (AtomicInteger) element.getObjectKey();
        if(retryCount.incrementAndGet()>5){
            //重复过多的错误
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
            passwordRetryCache.remove(username);
        }

        return matches;
    }
}
