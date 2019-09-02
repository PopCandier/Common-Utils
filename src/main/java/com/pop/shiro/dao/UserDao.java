package com.pop.shiro.dao;

import com.pop.shiro.entity.User;

import java.util.Set;

public interface UserDao {

    public void update(User user);
    public User findOne(Long userId);

    public User createUser(User user);//创建账户
    public void changePassword(Long userId,String newPasssword);//修改密码
    public void correlationRoles(Long userId,
                                 Long... roleIds);//添加用户-权限权限
    public void uncorrelationRoles(Long userId,//移除用户-权限关系
                                   Long... roleIds);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 通过用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
