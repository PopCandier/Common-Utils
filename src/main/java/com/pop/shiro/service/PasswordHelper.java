package com.pop.shiro.service;

import com.pop.shiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @program: Common-Utils
 * @description: password helper
 * @author: 范凌轩
 * @create: 2019-05-14 16:16
 **/
public class PasswordHelper {
    private RandomNumberGenerator
     randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations=2;
    public  void encryptPassword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations
        ).toHex();
        user.setPassword(newPassword);
    }
}
