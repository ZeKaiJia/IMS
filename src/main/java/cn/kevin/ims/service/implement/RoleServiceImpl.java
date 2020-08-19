package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.Role;
import cn.kevin.ims.mapper.RoleMapper;
import cn.kevin.ims.service.RoleService;
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
 * The type Role service.
 * 角色表/用户-角色表Service层接口实现类
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    private Role role;
    @Override
    public Role saveRoleInfo(@NotNull Role paramRole) {
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
    @Override
    public void createUserRole(String paramUsrType, String paramUsrName) {
        roleMapper.createUserRole(paramUsrType, paramUsrName);
    }
    @Override
    public Role deleteRole(Integer paramId) {
        role = roleMapper.selectById(paramId);
        if (role != null) {
            roleMapper.deleteRole(paramId);
            return role;
        } else {
            return null;
        }
    }
    @Override
    public void deleteUserRole(String paramUsrName) {
        roleMapper.deleteUserRole(paramUsrName);
    }
    @Override
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
    @Override
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
    @Override
    public Role updateRoleInfo(@NotNull Role paramRole) {
        role = roleMapper.selectById(paramRole.getId());
        if (role != null) {
            paramRole.setUtcModify(DateUtil.currentSecond());
            roleMapper.updateRoleInfo(paramRole);
            return paramRole;
        } else {
            return null;
        }
    }
    @Override
    public void changeUserRole(String paramUserType, String paramUserName) {
        roleMapper.changeUserRole(paramUserType, paramUserName);
    }
    @Override
    public Role selectById(Integer paramId) {
        return roleMapper.selectById(paramId);
    }
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
    @Override
    public List<Role> selectAnyParam(Role paramRole) {
        return roleMapper.selectAnyParam(paramRole);
    }
    @Override
    public Set<String> findRoleByUserName(String usrName) {
        return roleMapper.findRoleByUserName(usrName);
    }
    @Override
    public List<String> selectAllUserRole() {
        return roleMapper.selectAllUserRole();
    }
}
