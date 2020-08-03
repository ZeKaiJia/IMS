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
import java.util.List;
import java.util.Set;


/**
 * The type Role service.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class RoleService {
    /**
     * The Role mapper.
     */
    @Resource
    private RoleMapper roleMapper;
    /**
     * The Roles.
     */
    private List<Role> roles;
    /**
     * The Role.
     */
    private Role role;

    /**
     * Save role info role.
     * 添加角色
     * @param paramRole the param role
     * @return the role
     */
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

    /**
     * Delete role role.
     * 删除角色
     * @param paramId the param id
     * @return the role
     */
    public Role deleteRole(Integer paramId) {
        role = roleMapper.selectById(paramId);
        if (role != null) {
            roleMapper.deleteRole(paramId);
            return role;
        } else {
            return null;
        }
    }

    /**
     * Disable role role.
     * 禁用角色
     * @param paramId the param id
     * @return the role
     */
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

    /**
     * Recover role role.
     * 恢复角色
     * @param paramId the param id
     * @return the role
     */
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

    /**
     * Update role info role.
     * 更新角色
     * @param paramRole the param role
     * @return the role
     */
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

    /**
     * Select by id role.
     * 查找角色
     * @param paramId the param id
     * @return the role
     */
    public Role selectById(Integer paramId) {
        return roleMapper.selectById(paramId);
    }

    /**
     * Select all list.
     * 角色列表
     * @return the list
     */
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    /**
     * Select any param list.
     * 按任意字段查找角色
     * @param paramRole the param role
     * @return the list
     */
    public List<Role> selectAnyParam(Role paramRole) {
        return roleMapper.selectAnyParam(paramRole);
    }

    /**
     * Select any param list.
     * 根据用户名查找角色名
     * @param usrName the usr name
     * @return the set
     */
    public Set<String> findRoleByUserName(String usrName) {
        return roleMapper.findRoleByUserName(usrName);
    }
}
