package cn.kevin.ims.service;

import cn.kevin.ims.entity.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * The type Role service.
 * 角色表/用户-角色表Service层接口类
 * @author kevin
 */
public interface PermissionService {
    /**
     * Save permission info permission.
     * 根据权限对象插入权限数据
     * @param paramPermission the param permission
     * @return the permission
     */
    Permission savePermissionInfo(@NotNull Permission paramPermission);

    /**
     * Insert new permission string.
     * 根据角色名和权限名创建角色对应权限
     * @param paramRole       the param role
     * @param paramPermission the param permission
     * @return the string
     */
    String insertNewPermission(String paramRole, String paramPermission);

    /**
     * Delete permission permission.
     * 根据权限名删除权限
     * @param paramName the param name
     * @return the permission
     */
    Permission deletePermission(String paramName);

    /**
     * Delete role permission string.
     * 根据角色名删除角色对应权限
     * @param paramRole the param role
     * @return the string
     */
    String deleteRolePermission(String paramRole);

    /**
     * Disable permission permission.
     * 根据权限名禁用权限
     * @param paramName the param name
     * @return the permission
     */
    Permission disablePermission(String paramName);

    /**
     * Recover permission permission.
     * 根据权限名恢复权限
     * @param paramName the param name
     * @return the permission
     */
    Permission recoverPermission(String paramName);

    /**
     * Update permission info permission.
     * 根据权限对象更新权限数据
     * @param paramPermission the param permission
     * @return the permission
     */
    Permission updatePermissionInfo(@NotNull Permission paramPermission);

    /**
     * Select by name permission.
     * 根据权限名查询权限数据
     * @param paramName the param name
     * @return the permission
     */
    Permission selectByName(String paramName);

    /**
     * Select by id permission.
     * 根据ID查询权限数据
     * @param paramId the param id
     * @return the permission
     */
    Permission selectById(Integer paramId);

    /**
     * Select all list.
     * 查询权限列表
     * @return the list
     */
    List<Permission> selectAll();

    /**
     * Select any param list.
     * 根据任意字段查询权限数据
     * @param paramPermission the param permission
     * @return the list
     */
    List<Permission> selectAnyParam(Permission paramPermission);

    /**
     * Find permission by role set.
     * 根据角色名查询角色对应权限
     * @param paramRole the param role
     * @return the set
     */
    Set<String> findPermissionByRole(String paramRole);

}
