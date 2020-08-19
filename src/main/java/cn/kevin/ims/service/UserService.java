package cn.kevin.ims.service;

import cn.kevin.ims.entity.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The interface User service.
 * 用户表Service层接口类
 * @Author: Kevin
 * @Date: 2020 /8/19 3:06 下午
 */
public interface UserService {
    /**
     * Save user info user.
     * 根据用户对象插入用户信息
     * @param paramUser the param user
     * @return the user
     */
    User saveUserInfo(@NotNull User paramUser);

    /**
     * Delete user user.
     * 根据用户名删除用户信息
     * @param paramUserName the param user name
     * @return the user
     */
    User deleteUser(String paramUserName);

    /**
     * Disable user user.
     * 根据用户名禁用用户账户
     * @param paramUserName the param user name
     * @return the user
     */
    User disableUser(String paramUserName);

    /**
     * Recover user user.
     * 根据用户名恢复用户账户
     * @param paramUserName the param user name
     * @return the user
     */
    User recoverUser(String paramUserName);

    /**
     * Update user info user.
     * 根据用户对象更新用户数据
     * @param paramUser the param user
     * @return the user
     */
    User updateUserInfo(@NotNull User paramUser);

    /**
     * User login user.
     * 根据用户名和密码登录用户
     * @param paramUserName     the param user name
     * @param paramUserPassword the param user password
     * @return the user
     */
    User userLogin(String paramUserName, String paramUserPassword);

    /**
     * Select by name user.
     * 根据用户名查找用户
     * @param paramUserName the param user name
     * @return the user
     */
    User selectByName(String paramUserName);

    /**
     * Select all list.
     * 查找用户列表
     * @return the list
     */
    List<User> selectAll();

    /**
     * Select any param list.
     * 根据任意字段查找用户
     * @param paramUser the param user
     * @return the list
     */
    List<User> selectAnyParam(User paramUser);
}
