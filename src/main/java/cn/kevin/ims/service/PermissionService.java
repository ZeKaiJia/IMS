package cn.kevin.ims.service;

import cn.kevin.ims.entity.Permission;
import cn.kevin.ims.mapper.PermissionMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * The type Permission service.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class PermissionService {
    /**
     * The Permission mapper.
     */
    @Resource
    private PermissionMapper permissionMapper;
    /**
     * The Permissions.
     */
    private List<Permission> permissions;
    /**
     * The Permission.
     */
    private Permission permission;

    /**
     * Save permission info permission.
     * 添加权限
     * @param paramPermission the param permission
     * @return the permission
     */
    public Permission savePermissionInfo(Permission paramPermission) {
        permission = permissionMapper.selectByName(paramPermission.getName());
        if (permission == null) {
            paramPermission.setUtcCreate(DateUtil.currentSecond());
            paramPermission.setUtcModify(DateUtil.currentSecond());
            permissionMapper.savePermissionInfo(paramPermission);
            return paramPermission;
        } else {
            return permission;
        }
    }

    /**
     * Delete permission permission.
     * 删除权限
     * @param paramName the param name
     * @return the permission
     */
    public Permission deletePermission(String paramName) {
        permission = permissionMapper.selectByName(paramName);
        if (permission != null) {
            permissionMapper.deletePermission(paramName);
            return permission;
        } else {
            return null;
        }
    }

    /**
     * Disable permission permission.
     * 禁用权限
     * @param paramName the param name
     * @return the permission
     */
    public Permission disablePermission(String paramName) {
        permission = permissionMapper.selectByName(paramName);
        if (permission != null) {
            permission.setUtcModify(DateUtil.currentSecond());
            permission.setValid(false);
            permissionMapper.disablePermission(permission);
            return permission;
        } else {
            return null;
        }
    }

    /**
     * Recover permission permission.
     * 恢复权限
     * @param paramName the param name
     * @return the permission
     */
    public Permission recoverPermission(String paramName) {
       permission = permissionMapper.selectByName(paramName);
       if (permission != null) {
           permission.setUtcModify(DateUtil.currentSecond());
           permission.setValid(true);
           permissionMapper.recoverPermission(permission);
           return permission;
       } else {
           return null;
       }
    }

    /**
     * Update permission info permission.
     * 更新权限
     * @param paramPermission the param permission
     * @return the permission
     */
    public Permission updatePermissionInfo(Permission paramPermission) {
        permission = permissionMapper.selectByName(paramPermission.getName());
        if (permission != null) {
            paramPermission.setUtcModify(DateUtil.currentSecond());
            permissionMapper.updatePermissionInfo(paramPermission);
            return paramPermission;
        } else {
            return null;
        }
    }

    /**
     * Select by name permission.
     * 查找权限
     * @param paramName the param name
     * @return the permission
     */
    public Permission selectByName(String paramName) {
        return permissionMapper.selectByName(paramName);
    }

    /**
     * Select all list.
     * 权限列表
     * @return the list
     */
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    /**
     * Select any param list.
     * 按任意字段查找权限
     * @param paramPermission the param permission
     * @return the list
     */
    public List<Permission> selectAnyParam(Permission paramPermission) {
        return permissionMapper.selectAnyParam(paramPermission);
    }

    /**
     * Find permission by role.
     * 根据角色查找权限
     * @param role the role
     * @return the set
     */
    public Set<String> findPermissionByRole(String role) {
        return permissionMapper.findPermissionByRole(role);
    }
}
