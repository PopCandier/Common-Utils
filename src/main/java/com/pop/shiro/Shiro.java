package com.pop.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author Pop
 * @date 2019/4/14 20:46
 */
public class Shiro {

    @Test
    public void testHelloworld(){

        //注册工厂
        Subject subject = getSubject("classpath:shiro.ini");

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");


        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //断言
        assert true:subject.isAuthenticated();

        //退出
        subject.logout();
    }

    @Test
    public void testCustomRealm(){
        Subject subject = getSubject("classpath:shiro-realm.ini");
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");


        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //断言
        assert true:subject.isAuthenticated();

        //退出
        subject.logout();
    }

    private Subject getSubject(String path) {
        Factory<SecurityManager> factory
                = new IniSecurityManagerFactory(path);
        //设置实例
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        //得到Subject 以及粗昂见用户名密码身份验证Token
        return SecurityUtils.getSubject();
    }

}
