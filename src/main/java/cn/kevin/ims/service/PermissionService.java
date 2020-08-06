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

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    private List<Permission> permissions;
    private Permission permission;
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
    public Permission deletePermission(String paramName) {
        permission = permissionMapper.selectByName(paramName);
        if (permission != null) {
            permissionMapper.deletePermission(paramName);
            return permission;
        } else {
            return null;
        }
    }
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
    public Permission selectByName(String paramName) {
        return permissionMapper.selectByName(paramName);
    }
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }
    public List<Permission> selectAnyParam(Permission paramPermission) {
        return permissionMapper.selectAnyParam(paramPermission);
    }
    public Set<String> findPermissionByRole(String role) {
        return permissionMapper.findPermissionByRole(role);
    }
}
