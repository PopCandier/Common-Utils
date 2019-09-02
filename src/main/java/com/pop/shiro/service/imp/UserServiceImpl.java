package com.pop.shiro.service.imp;

import com.pop.shiro.dao.UserDao;
import com.pop.shiro.dao.UserDaoImpl;
import com.pop.shiro.entity.User;
import com.pop.shiro.service.PasswordHelper;
import com.pop.shiro.service.UserService;

import java.util.Set;

/**
 * @program: Common-Utils
 * @description: imp user service
 * @author: 范凌轩
 * @create: 2019-05-14 15:37
 **/
public class UserServiceImpl implements UserService {
    private PasswordHelper passwordHelper = new PasswordHelper();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String newPasssword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPasssword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
