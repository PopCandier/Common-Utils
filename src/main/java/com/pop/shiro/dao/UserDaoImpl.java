package com.pop.shiro.dao;

import com.pop.shiro.entity.User;

import java.util.Set;

/**
 * @program: Common-Utils
 * @description: dao的实现
 * @author: 范凌轩
 * @create: 2019-05-14 16:34
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void update(User user) {

    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void changePassword(Long userId, String newPasssword) {

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
