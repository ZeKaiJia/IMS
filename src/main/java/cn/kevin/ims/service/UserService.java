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
 * UserService
 * @author kevin
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserService {
    /**
     * The User mapper.
     */
    @Resource
    private UserMapper userMapper;

    /**
     * The User.
     */
    private User user;

    /**
     * Insert user.
     * 插入
     * @param usr the usr
     * @return the user
     */
    public User insert(User usr) {
        user = userMapper.selectAdminById(usr.getUsrId());
        if (user == null) {
            usr.setUtcCreate(DateUtil.currentSecond());
            usr.setUtcModify(DateUtil.currentSecond());
            userMapper.insert(usr);
            return usr;
        }
        return null;
    }


    /**
     * Save user.
     * 删除
     * @param usrId the usr id
     * @return the user
     */
    public User save(String usrId) {
        user = userMapper.select(usrId);
        if (user != null) {
            userMapper.save(usrId);
        }
        return user;
    }

    /**
     * Delete user.
     * 禁用
     * @param usrId the usr id
     * @return the user
     */
    public User delete(String usrId) {
        user = userMapper.select(usrId);
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setIsReal(false);
            userMapper.delete(user);
        }
        return user;
    }

    /**
     * Re delete user.
     * 恢复禁用
     * @param usrId the usr id
     * @return the user
     */
    public User reDelete(String usrId) {
        List<User> users = userMapper.selectAdmin();
        for (User u : users) {
            if (u.getUsrId().equals(usrId)) {
                user = u;
                break;
            }
        }
        if (user != null) {
            user.setUtcModify(DateUtil.currentSecond());
            user.setIsReal(true);
            userMapper.reDelete(user);
        }
        return user;
    }

    /**
     * Update user.
     * 更新
     * @param usr the usr
     * @return the user
     */
    public User update(User usr) {
        user = userMapper.select(usr.getUsrId());
        if (user != null) {
            usr.setUtcModify(DateUtil.currentSecond());
            usr.setUtcCreate(user.getUtcCreate());
            userMapper.update(usr);
            return usr;
        }
        return null;
    }

    /**
     * Login user.
     * 登录
     * @param usrId       the usr id
     * @param usrPassword the usr password
     * @return the user
     */
    public User login(String usrId, String usrPassword) {
        user = userMapper.select(usrId);
        if (user != null ) {
            if (user.getUsrPassword().equals(usrPassword)) {
                user.setLastLogin(DateUtil.currentSecond());
                userMapper.login(user);
            }
            return user;
        }
        return null;
    }

    /**
     * Select user.
     * 查找单个非禁用数据
     * @param usrId the usr id
     * @return the user
     */
    public User select(String usrId) {
        return userMapper.select(usrId);
    }

    /**
     * Select all list.
     * 查找所有非禁用数据
     * @return the list
     */
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * Select by all info list.
     * 按任意字段查找非禁用数据
     * @param user the user
     * @return the list
     */
    public List<User> selectByAllInfo(User user) {
        return userMapper.selectByAllInfo(user);
    }

    /**
     * Select admin by id user.
     * 查找单个数据
     * @param usrId the usr id
     * @return the user
     */
    public User selectAdminById(String usrId) {
        return userMapper.selectAdminById(usrId);
    }

    /**
     * Select garbage list.
     * 查找禁用数据
     * @return the list
     */
    public List<User> selectGarbage() {
        return userMapper.selectGarbage();
    }

    /**
     * Select admin list.
     * 查找所有数据
     * @return the list
     */
    public List<User> selectAdmin() {
        return userMapper.selectAdmin();
    }
}
