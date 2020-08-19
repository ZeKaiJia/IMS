package cn.kevin.ims.service.implement;

import cn.kevin.ims.entity.User;
import cn.kevin.ims.mapper.UserMapper;
import cn.kevin.ims.service.UserService;
import cn.kevin.ims.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type User service.
 * @author kevin
 * 用户表Service层接口实现类
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    private User user;
    @Override
    public User saveUserInfo(@NotNull User paramUser) {
        user = userMapper.selectByName(paramUser.getUsrName());
        if (user == null) {
            paramUser.setUtcCreate(DateUtil.currentSecond());
            paramUser.setUtcModify(DateUtil.currentSecond());
            userMapper.saveUserInfo(paramUser);
            return paramUser;
        } else {
            return null;
        }
    }
    @Override
    public User deleteUser(String paramUserName) {
        user = userMapper.selectByName(paramUserName);
        if (user != null) {
            userMapper.deleteUser(paramUserName);
            return user;
        } else {
            return null;
        }
    }
    @Override
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
    @Override
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
    @Override
    public User updateUserInfo(@NotNull User paramUser) {
        user = userMapper.selectByName(paramUser.getUsrName());
        if (user != null) {
            paramUser.setUtcModify(DateUtil.currentSecond());
            userMapper.updateUserInfo(paramUser);
            return paramUser;
        } else {
            return null;
        }
    }
    @Override
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
    @Override
    public User selectByName(String paramUserName) {
        return userMapper.selectByName(paramUserName);
    }
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
    @Override
    public List<User> selectAnyParam(User paramUser) {
        return userMapper.selectAnyParam(paramUser);
    }
}
