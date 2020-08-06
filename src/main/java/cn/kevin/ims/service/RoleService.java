package cn.kevin.ims.service;

import cn.kevin.ims.entity.Role;
import cn.kevin.ims.entity.User;
import cn.kevin.ims.mapper.RoleMapper;
import cn.kevin.ims.mapper.UserMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * The type Role service.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class RoleService {
    @Resource
    private RoleMapper roleMapper;
    private List<Role> roles;
    private Role role;
    public Role saveRoleInfo(Role paramRole) {
        role = roleMapper.selectById(paramRole.getId());
        if (role == null) {
            paramRole.setUtcCreate(DateUtil.currentSecond());
            paramRole.setUtcModify(DateUtil.currentSecond());
            roleMapper.saveRoleInfo(paramRole);
            return paramRole;
        } else {
            return role;
        }
    }
    public void createUserRole(String paramUsrType, String paramUsrName) {
        roleMapper.createUserRole(paramUsrType, paramUsrName);
    }
    public Role deleteRole(Integer paramId) {
        role = roleMapper.selectById(paramId);
        if (role != null) {
            roleMapper.deleteRole(paramId);
            return role;
        } else {
            return null;
        }
    }
    public void deleteUserRole(String paramUsrName) {
        roleMapper.deleteUserRole(paramUsrName);
    }
    public Role disableRole(Integer paramId) {
        role = roleMapper.selectById(paramId);
        if (role != null) {
            role.setUtcModify(DateUtil.currentSecond());
            role.setValid(false);
            roleMapper.disableRole(role);
            return role;
        } else {
            return null;
        }
    }
    public Role recoverRole(Integer paramId) {
       role = roleMapper.selectById(paramId);
       if (role != null) {
           role.setUtcModify(DateUtil.currentSecond());
           role.setValid(true);
           roleMapper.recoverRole(role);
           return role;
       } else {
           return null;
       }
    }
    public Role updateRoleInfo(Role paramRole) {
        role = roleMapper.selectById(paramRole.getId());
        if (role != null) {
            paramRole.setUtcModify(DateUtil.currentSecond());
            roleMapper.updateRoleInfo(paramRole);
            return paramRole;
        } else {
            return null;
        }
    }
    public void changeUserRole(String paramUserType, String paramUserName) {
        roleMapper.changeUserRole(paramUserType, paramUserName);
    }
    public Role selectById(Integer paramId) {
        return roleMapper.selectById(paramId);
    }
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
    public List<Role> selectAnyParam(Role paramRole) {
        return roleMapper.selectAnyParam(paramRole);
    }
    public Set<String> findRoleByUserName(String usrName) {
        return roleMapper.findRoleByUserName(usrName);
    }
    public List<String> selectAllUserRole() {
        return roleMapper.selectAllUserRole();
    }
}
