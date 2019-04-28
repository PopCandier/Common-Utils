package com.pop.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author Pop
 * @date 2019/4/14 21:33
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        String password = (String)token.getCredentials();

        if(!"zhan".equals(username)){
            throw new UnknownAccountException();//账号错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();//密码错误
        }

        //返回用户信息
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
