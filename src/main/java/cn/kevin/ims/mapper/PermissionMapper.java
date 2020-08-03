package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 权限Mapper
 * The interface Permission mapper.
 */
@Repository
public interface PermissionMapper {
    /**
     * Save permission info.
     * 添加权限
     * @param permission the permission
     */
    void savePermissionInfo(Permission permission);

    /**
     * Delete permission.
     * 删除权限
     * @param name the name
     */
    void deletePermission(String name);

    /**
     * Disable permission.
     * 禁用权限
     * @param permission the permission
     */
    void disablePermission(Permission permission);

    /**
     * Recover permission.
     * 恢复权限
     * @param permission the permission
     */
    void recoverPermission(Permission permission);

    /**
     * Update permission info.
     * 更新权限
     * @param permission the permission
     */
    void updatePermissionInfo(Permission permission);

    /**
     * Select by name permission.
     * 按名称查找
     * @param name the name
     * @return the permission
     */
    Permission selectByName(String name);

    /**
     * Select all list.
     * 权限列表
     * @return the list
     */
    List<Permission> selectAll();

    /**
     * Select any param list.
     * 按任意字段查找
     * @param permission the permission
     * @return the list
     */
    List<Permission> selectAnyParam(Permission permission);

    /**
     * Find permission by role.
     * 根据角色查找权限
     * @param role the role
     * @return the set
     */
    Set<String> findPermissionByRole(String role);
}
