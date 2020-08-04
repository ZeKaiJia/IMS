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

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class UserService {
    @Resource
    private UserMapper userMapper;
    private List<User> users;
    private User user;
    public User saveUserInfo(User paramUser) {
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
    public User deleteUser(String paramUserName) {
        user = userMapper.selectByName(paramUserName);
        if (user != null) {
            userMapper.deleteUser(paramUserName);
            return user;
        } else {
            return null;
        }
    }
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
    public User selectByName(String paramUserName) {
        return userMapper.selectByName(paramUserName);
    }
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
    public List<User> selectAnyParam(User paramUser) {
        return userMapper.selectAnyParam(paramUser);
    }
}
