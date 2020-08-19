package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.Permission;
import cn.kevin.ims.mapper.PermissionMapper;
import cn.kevin.ims.service.PermissionService;
import cn.kevin.ims.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * The type Permission service.
 * 权限表/角色-权限表Service层接口实现类
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    private Permission permission;
    @Override
    public Permission savePermissionInfo(@NotNull Permission paramPermission) {
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
    @Override
    public String insertNewPermission(String paramRole, String paramPermission) {
        permissionMapper.insertNewPermission(paramRole, paramPermission);
        return "Success!";
    }
    @Override
    public Permission deletePermission(String paramName) {
        permission = permissionMapper.selectByName(paramName);
        if (permission != null) {
            permissionMapper.deletePermission(paramName);
            return permission;
        } else {
            return null;
        }
    }
    @Override
    public String deleteRolePermission(String paramRole) {
        permissionMapper.deleteRolePermission(paramRole);
        return "Success!";
    }
    @Override
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
    @Override
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
    @Override
    public Permission updatePermissionInfo(@NotNull Permission paramPermission) {
        permission = permissionMapper.selectById(paramPermission.getId());
        if (permission != null) {
            paramPermission.setUtcModify(DateUtil.currentSecond());
            permissionMapper.updatePermissionInfo(paramPermission);
            return paramPermission;
        } else {
            return null;
        }
    }
    @Override
    public Permission selectByName(String paramName) {
        return permissionMapper.selectByName(paramName);
    }
    @Override
    public Permission selectById(Integer paramId) {
        return permissionMapper.selectById(paramId);
    }
    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }
    @Override
    public List<Permission> selectAnyParam(Permission paramPermission) {
        return permissionMapper.selectAnyParam(paramPermission);
    }
    @Override
    public Set<String> findPermissionByRole(String paramRole) {
        return permissionMapper.findPermissionByRole(paramRole);
    }
}
