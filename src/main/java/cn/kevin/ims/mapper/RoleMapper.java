package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * The interface Role mapper.
 */
@Repository
public interface RoleMapper {
    /**
     * Save role info.
     * 添加角色
     * @param role the role
     */
    void saveRoleInfo(Role role);

    /**
     * Delete role.
     * 删除角色
     * @param id the id
     */
    void deleteRole(Integer id);

    /**
     * Disable role.
     * 禁用角色
     * @param role the role
     */
    void disableRole(Role role);

    /**
     * Recover role.
     * 恢复角色
     * @param role the role
     */
    void recoverRole(Role role);

    /**
     * Update role info.
     * 更新角色
     * @param role the role
     */
    void updateRoleInfo(Role role);

    /**
     * Select by id role.
     * 查找角色
     * @param id the id
     * @return the role
     */
    Role selectById(Integer id);

    /**
     * Select all list.
     * 角色列表
     * @return the list
     */
    List<Role> selectAll();

    /**
     * Select any param list.
     * 按任意字段查找角色
     * @param role the role
     * @return the list
     */
    List<Role> selectAnyParam(Role role);
    /**
     * Find Role By User Name.
     * 按用户名查找用户角色
     * @param usrName the usrName
     * @return the set
     */
    Set<String> findRoleByUserName(String usrName);
}
