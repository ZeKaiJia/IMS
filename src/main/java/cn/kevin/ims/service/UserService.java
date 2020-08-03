package cn.kevin.ims.service;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.mapper.UserMapper;
import cn.kevin.ims.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type User service.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserService {
    /**
     * The Sys user mapper.
     */
    @Resource
    private UserMapper userMapper;
    /**
     * The Sys users.
     */
    private List<User> users;
    /**
     * The Sys user.
     */
    private User user;

    /**
     * Save user info sys user.
     * 添加用户
     * @param paramUser the param user
     * @return the sys user
     */
    public User saveUserInfo(User paramUser) {
        user = userMapper.selectByName(paramUser.getUsrName());
        if (user == null) {
            paramUser.setUtcCreate(DateUtil.currentSecond());
            paramUser.setUtcModify(DateUtil.currentSecond());
            userMapper.saveUserInfo(paramUser);
            return paramUser;
        } else {
            return user;
        }
    }

    /**
     * Delete user sys user.
     * 删除用户
     * @param paramUserName the param user name
     * @return the sys user
     */
    public User deleteUser(String paramUserName) {
        user = userMapper.selectByName(paramUserName);
        if (user != null) {
            userMapper.deleteUser(paramUserName);
            return user;
        } else {
            return null;
        }
    }

    /**
     * Disable user sys user.
     * 禁用用户
     * @param paramUserName the param user name
     * @return the sys user
     */
    public User disableUser(String paramUserName) {
        user = userMapper.selectByName(paramUserName);
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setValid(false);
            userMapper.disableUser(user);
            return user;
        } else {
            return null;
        }
    }

    /**
     * Recover user sys user.
     * 恢复用户
     * @param paramUserName the param user name
     * @return the sys user
     */
    public User recoverUser(String paramUserName) {
       user = userMapper.selectByName(paramUserName);
       if (user != null) {
           user.setUtcModify(DateUtil.currentSecond());
           user.setValid(true);
           userMapper.recoverUser(user);
           return user;
       } else {
           return null;
       }
    }

    /**
     * Update user info sys user.
     * 更新用户
     * @param paramUser the param user
     * @return the sys user
     */
    public User updateUserInfo(User paramUser) {
        user = userMapper.selectByName(paramUser.getUsrName());
        if (user != null) {
            paramUser.setUtcModify(DateUtil.currentSecond());
            userMapper.updateUserInfo(paramUser);
            return paramUser;
        } else {
            return null;
        }
    }

    /**
     * User login sys user.
     * 用户登录
     * @param paramUserName     the param user name
     * @param paramUserPassword the param user password
     * @return the sys user
     */
    public User userLogin(String paramUserName, String paramUserPassword) {
        user = userMapper.selectByName(paramUserName);
        if (user != null ) {
            if (user.getUsrPassword().equals(paramUserPassword)) {
                user.setLastLogin(DateUtil.currentSecond());
                userMapper.userLogin(user);
            }
            return user;
        }
        return null;
    }

    /**
     * Select by name sys user.
     * 根据用户名查找用户
     * @param paramUserName the param user name
     * @return the sys user
     */
    public User selectByName(String paramUserName) {
        return userMapper.selectByName(paramUserName);
    }

    /**
     * Select all list.
     * 用户列表
     * @return the list
     */
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * Select any param list.
     * 按任意字段查找用户
     * @param paramUser the param user
     * @return the list
     */
    public List<User> selectAnyParam(User paramUser) {
        return userMapper.selectAnyParam(paramUser);
    }
}
