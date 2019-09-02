package com.pop.shiro.entity;

/**
 * @program: Common-Utils
 * @description: domain of Role
 * @author: 范凌轩
 * @create: 2019-05-14 15:08
 **/
public class Role {
    private String id;
    private String role;
    private String description;
    private Boolean available;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
