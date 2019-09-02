package com.pop.shiro.service;

import com.pop.shiro.entity.Role;

/**
 * 角色的service
 * 角色和权限是多对多关系
 */
public interface RoleService {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 建立角色和权限之间的关系
     * @param roleId
     * @param permissonIds
     */
    public void correlationPermissions(Long roleId, Long... permissonIds);

    /**
     * 解除角色和权限之间的关系
     * @param roleId
     * @param permissonIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissonIds);
}
