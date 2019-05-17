package com.pop.shiro.entity;

/**
 * @program: Common-Utils
 * @description: 用户角色
 * @author: 范凌轩
 * @create: 2019-05-14 15:09
 **/
public class User {
    private String id;
    private String username;
    private String password;
    private String salt;
    private Boolean locked=Boolean.FALSE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getCredentialsSalt(){
        return username+salt;
    }
}
