package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 * The interface Sys user mapper.
 */
@Repository
public interface UserMapper {
    /**
     * Save user info.
     * 创建用户
     * @param sysUser the sys user
     */
    void saveUserInfo(User sysUser);

    /**
     * Delete user.
     * 删除用户
     * @param usrName the usr name
     */
    void deleteUser(String usrName);

    /**
     * Disable user.
     * 禁用用户
     * @param sysUser the sys user
     */
    void disableUser(User sysUser);

    /**
     * Recover user.
     * 恢复用户
     * @param sysUser the sys user
     */
    void recoverUser(User sysUser);

    /**
     * Update user info.
     * 更新用户
     * @param sysUser the sys user
     */
    void updateUserInfo(User sysUser);

    /**
     * User login.
     * 用户登录
     * @param sysUser the sys user
     */
    void userLogin(User sysUser);

    /**
     * Select by name sys user.
     * 根据用户名查找
     * @param usrName the usr name
     * @return the sys user
     */
    User selectByName(String usrName);

    /**
     * Select all list.
     * 查找全部
     * @return the list
     */
    List<User> selectAll();

    /**
     * Select any param list.
     * 按任意字段查找
     * @param sysUser the sys user
     * @return the list
     */
    List<User> selectAnyParam(User sysUser);
}
