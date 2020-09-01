package cn.kevin.ims.service;

import cn.kevin.ims.entity.Role;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * The type Role service.
 * 角色表/用户-角色表Service层接口类
 * @author kevin
 */
public interface RoleService {
    /**
     * Save role info role.
     * 根据角色对象插入角色数据
     * @param paramRole the param role
     * @return the role
     */
    Role saveRoleInfo(@NotNull Role paramRole);

    /**
     * Create user role.
     * 根据用户角色和用户名创建用户对应角色
     * @param paramUsrType the param usr type
     * @param paramUsrName the param usr name
     */
    void createUserRole(String paramUsrType, String paramUsrName);

    /**
     * Delete role role.
     * 根据ID删除角色
     * @param paramId the param id
     * @return the role
     */
    Role deleteRole(Integer paramId);

    /**
     * Delete user role.
     * 根据用户名删除用户对应角色
     * @param paramUsrName the param usr name
     */
    void deleteUserRole(String paramUsrName);

    /**
     * Recover role role.
     * 根据ID禁用角色
     * @param paramId the param id
     * @return the role
     */
    Role disableRole(Integer paramId);

    /**
     * Recover role role.
     * 根据ID恢复角色
     * @param paramId the param id
     * @return the role
     */
    Role recoverRole(Integer paramId);

    /**
     * Update role info role.
     * 根据角色对象更新角色数据
     * @param paramRole the param role
     * @return the role
     */
    Role updateRoleInfo(@NotNull Role paramRole);

    /**
     * Change user role.
     * 根据用户角色和用户名更新用户对应角色
     * @param paramUserType the param user type
     * @param paramUserName the param user name
     */
    void changeUserRole(String paramUserType, String paramUserName);

    /**
     * Select by id role.
     * 根据ID查询角色
     * @param paramId the param id
     * @return the role
     */
    Role selectById(Integer paramId);
    /**
     * Select by id role.
     * 查询角色列表
     * @return the role
     */
    List<Role> selectAll();
    /**
     * Select any param list.
     * 根据任意字段查询角色
     * @param paramRole the param role
     * @return the list
     */
    List<Role> selectAnyParam(Role paramRole);

    /**
     * Find role by user name set.
     * 根据用户名查询用户对应角色
     * @param usrName the usr name
     * @return the set
     */
    Set<String> findRoleByUserName(String usrName);

    /**
     * Select all user role list.
     * 查询用户对应角色列表
     * @return the list
     */
    List<String> selectAllUserRole();
}
