package com.pop.shiro.service;

import org.apache.shiro.authz.Permission;

/**
 * 权限的service
 * 许可
 */
public interface PermissionService {
    /**
     * 创建一个权限
     * @param permission
     * @return
     */
    public Permission createPermission(Permission permission);

    /**
     * 删除权限的页面
     * @param permisssionId
     */
    public void deletePermission(Long permisssionId);
}
